#!/bin/bash

#
# Download the import data from AWS
#
set -x -e
PWD=$(cd `dirname "$0"`/..; pwd)

CLEAN=NO
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

YELP_IMPORT_DIR="${PWD}/target/import"

if [ "${CLEAN}" == "YES" ]; then
    rm -rf "${YELP_IMPORT_DIR}"
fi

mkdir -p "${YELP_IMPORT_DIR}"
aws s3 sync "s3://csiro-hadoop-pub/mn/yelp-2.0/"  "${YELP_IMPORT_DIR}"



