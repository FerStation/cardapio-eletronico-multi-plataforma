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

    $IDPedido = $_GET['id'];
    $total = 0;
    
    //seleciona os dados da tabela produto
    $query = mysql_query("SELECT lista_de_produtos.ID, lista_de_produtos.ID_pedido, lista_de_produtos.ID_produto, lista_de_produtos.quantidade, lista_de_produtos.pedido_efetuado, produtos.ID AS cod_produto, produtos.nome_produto, produtos.preco   FROM lista_de_produtos INNER JOIN produtos ON lista_de_produtos.ID_produto = produtos.ID WHERE lista_de_produtos.ID_pedido=$IDPedido");
    
    echo "<table id=\"tabelaConta\" class=\"table table-condensed\" style=\"margin-top: 2em;\">
          <caption><h1>Minha Conta</h1><br></caption>
          <thead style=\"margin-top: 2em;\">
              <tr>
                <th>Qtd</th>
                <th>Produto</th>
                <th>Preço</th>
                <th></th>
                </tr>
          </thead>
        <tbody>";
    
    while($prod = mysql_fetch_array($query)) {
        
        if($prod['pedido_efetuado'] == "Y"){
            
            echo "<tr class=\"success\" style=\"text-align: center; vertical-align: middle;\">
                        <td>".$prod['quantidade']."</td>
                        <td>".$prod['nome_produto']."</td>
                        <td>R$ ".($prod['quantidade'] * $prod['preco'])."</td>
                        <td><i class=\"icon-ok\"></i> Pedido</td>
            </tr>";
            
            $total += ($prod['quantidade'] * $prod['preco']);
        }
        else{
            
            echo "<tr id=".$prod['ID']." class=\"warning\" style=\"text-align: center;\">
                        <td>".$prod['quantidade']."</td>
                        <td>".$prod['nome_produto']."</td>
                        <td>R$ ".($prod['quantidade'] * $prod['preco'])."</td>
                        <td><button id=\"botaoDeletar\" type=\"button\" class=\"btn-mini btn-danger\" onclick=\"deleteMe(".$prod['ID'].",".$prod['quantidade'].",".$prod['preco'].");\"><i class=\"icon-remove icon-white\"></i> Excluir</button></td>
            </tr>";
            
            $total += ($prod['quantidade'] * $prod['preco']);
        }
    }
    
    echo "</tbody>
        <tfoot>
            <tr>
                <th>Total: </th>
                <th></th>
                <th>R$ ".$total."</th>
            </tr>
        </tfoot>
	</table>
        </div>";
?>
