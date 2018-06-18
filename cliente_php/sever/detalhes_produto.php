<?php

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
    
    //Informa qual o conjunto de caracteres será usado.
    header('Content-Type: text/html; charset=utf-8');

    //chama o arquivo de configuração com o banco
    require 'conexao.php';

    $IDProduto = $_GET['id'];
    
    //acerta pontuação e ç em utf-8
    mysql_query("SET NAMES 'utf8'");
    mysql_query('SET character_set_connection=utf8');
    mysql_query('SET character_set_client=utf8');
    mysql_query('SET character_set_results=utf8');
    
    //seleciona os dados da tabela produto
    $query = mysql_query("SELECT * FROM produtos WHERE ID=$IDProduto");

    echo "<div class=\"panel\" data-footer=\"footer_0\" selected=\"selected\" data-appbuilder-object=\"page\" data-header=\"header_0\">";
    
    while($prod = mysql_fetch_array($query)) {
    
        echo "<div style=\"width: 100%; heigth: 100%;\">
    <img class=\"img-polaroid\" src='img/produto/".$prod['imagem_produto']."' style='width: 100%; height: 8%;'>
    <table>
    <tr>
    <td><h2>".$prod['nome_produto']." </h2></td>
    <td style=\"text-align: right;\"><h2> | R$ ".$prod['preco']."</h2></td>
    </tr>
    <table>
    <div style=\"width: 100%; height: 3.8em; overflow-y: scroll;\">
    ".$prod['ingredientes']."
    </div>
    <button type=\"button\" class=\"btn btn-primary\" onclick=\"salvaVariaveis(".$prod['ID'].",".$prod['preco'].",'".$prod['nome_produto']."');\" data-toggle=\"modal\" data-target=\"#myModal\" style=\"width: 100%;\">Adicionar</button>
             </div>";
    }
    
    echo "</div>";
    
?>
