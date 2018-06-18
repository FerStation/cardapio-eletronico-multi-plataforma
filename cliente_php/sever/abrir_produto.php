<?php

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

//Informa qual o conjunto de caracteres será usado.
header('Content-Type: text/html; charset=utf-8');

//chama o arquivo de configuração com o banco
require 'conexao.php';

//acerta pontuação e ç em utf-8
mysql_query("SET NAMES 'utf8'");
mysql_query('SET character_set_connection=utf8');
mysql_query('SET character_set_client=utf8');
mysql_query('SET character_set_results=utf8');

$IDSubCategoria = $_GET['acc']; 

//seleciona os dados da tabela produto
$query = mysql_query("SELECT * FROM produtos WHERE ID_sub_categoria= $IDSubCategoria");

echo "<div class=\"panel\" data-footer=\"footer_0\" selected=\"selected\" data-appbuilder-object=\"page\" data-header=\"header_0\">";

while($prod = mysql_fetch_array($query)) {
    
    echo "<ul class=\"list\" data-appbuilder-object=\"list\" data-position=\"static\">
            <li>
                <a href='sever/detalhes_produto.php?id=".$prod['ID']."' style=\"font-size: 1.9em;\"><table><tr><td style='height: 30%; width: 40%;'><img src='img/produto/".$prod['imagem_produto']."' style='width: 100%;'></td><td> ".$prod['nome_produto']."<br><br>R$ ".$prod['preco']."</td></tr></table></a>
            </li>
          </ul>";
}

echo "</div>";

?>
