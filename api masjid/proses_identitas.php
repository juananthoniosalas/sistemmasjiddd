<?php

include("config.php");

$nama_masjid = $_POST['nama_masjid'];
$alamat_masjid = $_POST['alamat_masjid'];

$sql = "UPDATE identitas_masjid SET nama_masjid='$nama_masjid', alamat_masjid='$alamat_masjid' WHERE id_identitas='4'";
$query = mysqli_query($db, $sql);

if($query){

}else{
  die("gagal update..");
}
?>
