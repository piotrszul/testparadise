#!/bin/bash

#
# Download the import data from AWS
#
set  -e
PWD=$(cd `dirname "$0"`/..; pwd)

while [[ $# -gt 0 ]]; do
	case $1 in
	    -c|--clean)
	    CLEAN=YES
	    shift # past argument
	    ;;
	    *)    # unknown option
	    shift # past argument
	    ;;
	esac
done

# Common bootstrap
if [ -f "$HOME/.gviz.sh" ]; then
    echo "Sourcing: $HOME/.gviz.sh"
    source "$HOME/.gviz.sh"
fi

if [ -z "${NEO4J_HOME}" ]; then
    echo "You need set NEO4J_HOME. Can use \$HOME/.gviz.sh for that"
    exit 1
fi

DEST_DB="${NEO4J_HOME}/data/databases/paradiso.db"
MISC_DIR="${PWD}/misc"
IMPORT_DIR="${PWD}/target"

NODES=$(for i in $IMPORT_DIR/node/*.csv; do echo `basename ${i%.*}`; done)
RELATIONS=$(for i in $IMPORT_DIR/rel/*.csv; do echo `basename ${i%.*}`; done)
echo $NODES
echo $RELATIONS

rm -rf "${DEST_DB}"

sh <<EOF
"${NEO4J_HOME}/bin/neo4j-import" --into "${DEST_DB}" \
 $(for n in $NODES; do echo -n " --nodes:${n} ${MISC_DIR}/neo4j-node-header.csv,${IMPORT_DIR}/node/${n}.csv"; done) \
 $(for r in $RELATIONS; do echo -n " --relationships:${r} ${MISC_DIR}/neo4j-relation-header.csv,${IMPORT_DIR}/rel/${r}.csv"; done)
EOF
# --relationships:TIPPED "${YELP_IMPORT_DIR}/rel-tipped-header.csv`for i in ${YELP_IMPORT_DIR}/rel-tipped/*.csv.gz; do echo -n ,$i; done`"

