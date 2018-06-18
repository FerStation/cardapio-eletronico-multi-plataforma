<?php

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

require 'conexao.php';
    
    $id = $_POST['id'];
        
    mysql_query("DELETE FROM lista_de_produtos WHERE ID=$id") or die(mysql_error());
?>
