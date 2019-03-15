from flask import Flask, url_for, jsonify, abort
from neo4j import GraphDatabase 

app = Flask(__name__)

driver = GraphDatabase.driver("bolt://localhost:7687", auth=("neo4j", "maggie26"))


def path_to_ext(p):              
    return [ node_to_ref(n) for n in p.nodes] 

def node_to_ext(n):              
    return dict(id = n.id, labels = list(n.labels), properties = dict(n))

def node_to_ref(n):
    return dict(id = n.id, labels = list(n.labels), name = n['name'],
            uri=url_for('get_node', node_id = n.id, _external=True))

@app.route('/')
def index():
    return 'index'


@app.route('/node/<int:node_id>', methods=['GET'])
def get_node(node_id):
    with driver.session() as session:
       result = session.run('MATCH (n) WHERE ID(n)=$node_id RETURN n',node_id=node_id).single()
    if result is None:
        abort(404)
    else:
        return jsonify(node_to_ext(result['n']))
    

@app.route('/shortestPath/<int:start_id>/<int:end_id>',methods=['GET'])
def shortest_path(start_id, end_id):
    with driver.session() as session:
        result = session.run('MATCH path=shortestPath((b)-[*]-(e)) WHERE ID(b)=$start_id AND ID(e)=$end_id RETURN path', 
                start_id=start_id, end_id=end_id).single()

    if result is None:
        abort(404)
    else:
        return jsonify({'shortestPath': path_to_ext(result['path'])})

