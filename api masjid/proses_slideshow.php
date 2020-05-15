<?php

include("config.php");

$judul_slideshow = $_POST['judul_slideshow'];
$url_slideshow = $_POST['url_slideshow'];

$sql = "UPDATE slideshow SET judul_slideshow='$judul_slideshow', url_slideshow='$url_slideshow' WHERE id_slideshow='1'";
$query = mysqli_query($db, $sql);

if($query){

}else{
  die("gagal update..");
}
?>
