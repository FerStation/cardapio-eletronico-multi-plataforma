<?php

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

require 'conexao.php';

$idProduto = $_POST['produto'];
$qtd = $_POST['quantidade'];
$pedido = $_POST['pedido'];

mysql_query("INSERT INTO lista_de_produtos (ID_pedido, ID_produto, quantidade) VALUES($pedido,$idProduto,$qtd)") or die("errooo");


?>
