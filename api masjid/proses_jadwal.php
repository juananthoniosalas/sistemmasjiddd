<?php

include("config.php");

$shubuh = $_POST['shubuh'];
$dhuhur = $_POST['dhuhur'];
$ashar = $_POST['ashar'];
$maghrib = $_POST['maghrib'];
$isya = $_POST['isya'];
$dhuha = $_POST['dhuha'];

$sql = "UPDATE jadwal_sholat SET shubuh='$shubuh', dhuhur='$dhuhur', ashar='$ashar', maghrib='$maghrib', isya='$isya', dhuha='$dhuha' WHERE id_jadwal='1'";
$query = mysqli_query($db, $sql);

if($query){

}else{
  die("gagal update..");
}
?>
