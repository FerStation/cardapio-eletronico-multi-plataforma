<?php

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

require 'conexao.php';

    $IDPedido = $_GET['idd'];
    //echo "teste:".$IDPedido;
   mysql_query('UPDATE lista_de_produtos SET pedido_efetuado=\'Y\' WHERE ID_pedido='.$IDPedido) or die(mysql_error());
?>
