#!/bin/bash

#
# Download the import data from AWS
#
set -x -e
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

YELP_DB="${NEO4J_HOME}/data/databases/yelp.db"
IMPORT_DIR="${PWD}/target/import"
YELP_IMPORT_DIR="${IMPORT_DIR}/graph-neo4j"


rm -rf "${YELP_DB}"
"${NEO4J_HOME}/bin/neo4j-import" --into "${YELP_DB}" \
 --nodes:User "${YELP_IMPORT_DIR}/user-header.csv`for i in ${YELP_IMPORT_DIR}/user/*.csv.gz; do echo -n ,$i; done`" \
 --nodes:Business "${YELP_IMPORT_DIR}/business-header.csv`for i in ${YELP_IMPORT_DIR}/business/*.csv.gz; do echo -n ,$i; done`" \
 --relationships:FRIEND-OF "${YELP_IMPORT_DIR}/rel-friend_of-header.csv`for i in ${YELP_IMPORT_DIR}/rel-friend_of/*.csv.gz; do echo -n ,$i; done`"\
 --relationships:REVIEVED "${YELP_IMPORT_DIR}/rel-reviewed-header.csv`for i in ${YELP_IMPORT_DIR}/rel-reviewed/*.csv.gz; do echo -n ,$i; done`" \
 --relationships:TIPPED "${YELP_IMPORT_DIR}/rel-tipped-header.csv`for i in ${YELP_IMPORT_DIR}/rel-tipped/*.csv.gz; do echo -n ,$i; done`"

