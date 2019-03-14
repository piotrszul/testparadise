SELECT 
     node_1,node_2,idx,rel_type
FROM
   `edges`
WHERE
    rel_type=@rel
INTO OUTFILE '/Users/szu004/edu/graph/test/target/rel-registered_address.csv' 
FIELDS ENCLOSED BY '"' 
TERMINATED BY ',' 
ESCAPED BY '"' 
LINES TERMINATED BY '\n';
