<?php

mysql_connect("il.000webhost.com","ronkl","gingi123");//change server name  //pass username according your settings

mysql_select_db("id1474816_users");// also chang the Mysql database name

$sql1=mysql_query("select * from users ");

if (!$sql1) {

echo "Could not successfully run query ($sql) from DB: " . mysql_error();

exit;

}

while($row=mysql_fetch_assoc($sql1))

$output[]=$row;



print(json_encode($output));// this will print the output in json

mysql_close();

?>