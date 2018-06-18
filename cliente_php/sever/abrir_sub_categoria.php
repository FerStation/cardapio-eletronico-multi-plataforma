<?php

/*
 * Esse arquivo php é responsavel por abrir as sub categorias
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


$IDCategoria = $_GET['acc']; 

//seleciona os dados da tabela produto
$query = mysql_query("SELECT * FROM sub_categoria WHERE ID_categoria =$IDCategoria");

echo "<div class=\"panel\" data-footer=\"footer_0\" selected=\"selected\" data-appbuilder-object=\"page\" data-header=\"header_0\">";

while($prod = mysql_fetch_array($query)) {
    
    echo "<ul class=\"list\" data-appbuilder-object=\"list\" data-position=\"static\">
            <li>
                <a href=\"sever/abrir_produto.php?acc=".$prod['ID']."\" style=\"font-size: 1.9em;\"><table><tr><td style='height: 30%; width: 40%;'><img src='img/sub_categoria/".$prod['imagem_sub_categoria']."' style='width: 100%;'></td><td> ".$prod['nome']."</td></tr></table></a>
            </li>
          </ul>";
}

echo "</div>";

?>
