<?php

// MySQL credentials, as you can probably tell, our db security is off the charts....
$mysql_host = "mysql17.000webhost.com";
$mysql_database = "a2291907_qzdown";
$mysql_user = "a2291907_master";
$mysql_password = "asd123";

$con=mysqli_connect($mysql_host, $mysql_user, $mysql_password, $mysql_database);

if (mysqli_connect_errno($con))
{
   echo "Failed to connect to MySQL: " . mysqli_connect_error();
}

$rows = array();

$result = mysqli_query($con, "SELECT question, answer FROM `math_questions`");
while($r = mysqli_fetch_assoc($result)) {
    $rows[] = $r;
}

echo json_encode(array("data"=>$rows));

mysqli_close($con);

?>
