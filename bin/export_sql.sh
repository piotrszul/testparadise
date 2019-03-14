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

EXPORT_DIR=${PWD}/target

rm -rf ${EXPORT_DIR}
mkdir -p ${EXPORT_DIR}/node
mkdir -p ${EXPORT_DIR}/rel


PROPERTY_FIELDS="n.valid_until n.country_codes n.countries n.sourceID n.address n.name n.jurisdiction_description n.service_provider n.jurisdiction n.closed_date n.incorporation_date n.ibcRUC n.type n.status n.company_type n.note"
NODES=$(mysql -u crypto -pqwerty paradise -N -B -e "show tables" | grep "nodes" | awk -F "." '{print $2}')
echo $NODES

for NODE in ${NODES}; do
	mysql -u crypto -pqwerty paradise <<SQL
SELECT 
    \`n.node_id\`$(for p in $PROPERTY_FIELDS; do echo -n ",\`$p\`";done)
FROM
   \`nodes.${NODE}\`
INTO OUTFILE '${EXPORT_DIR}/node/${NODE}.csv' 
FIELDS ENCLOSED BY '"' 
TERMINATED BY ',' 
ESCAPED BY '"' 
LINES TERMINATED BY '\n';
SQL
done


for REL_TYPE in $(mysql -u crypto -pqwerty paradise -N -B -e "select distinct rel_type from edges"); do
mysql -u crypto -pqwerty paradise <<SQL
SELECT 
     node_1,node_2,idx,rel_type
FROM
   \`edges\`
WHERE
    rel_type='${REL_TYPE}'
INTO OUTFILE '${EXPORT_DIR}/rel/${REL_TYPE}.csv' 
FIELDS ENCLOSED BY '"' 
TERMINATED BY ',' 
ESCAPED BY '"' 
LINES TERMINATED BY '\n';
SQL
done

