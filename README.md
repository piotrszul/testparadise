

CYPHER QUERIES

	MATCH x=shortestPath((p)-[*]->(e))
	WHERE ID(p)=60201 AND ID(e)=9
	RETURN x



## Notes

- Embedded Neo4j: https://neo4j.com/docs/java-reference/3.5/tutorials-java-embedded/
- Simple Service with python: https://nicolewhite.github.io/neo4j-flask/pages/register-a-user.html



## Neo4j

- Pyhon Driver: https://github.com/neo4j/neo4j-python-driver
- Set Intial Password: https://neo4j.com/docs/operations-manual/current/configuration/set-initial-password/


# Flash rest: 

- https://blog.miguelgrinberg.com/post/designing-a-restful-api-with-python-and-flask
- http://flask.pocoo.org/docs/1.0/tutorial/tests/
