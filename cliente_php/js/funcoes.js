/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

quantidade;
var idProduto;

/**
 * 
 */
function salvaVariaveis(id,preco,nome){
             
             nomeProduto = nome;
             idProduto = id;
             precoProduto = preco;
             $("#my_select").find('option[value="01"]').attr("selected",true);
             
             $("#linhaNomeProduto").html(nome);
             $("#linhaPrecoProduto").html("R$ "+preco.toFixed(2));
             $("#linhaTotal").html("R$ "+(parseFloat(localStorage.total)+precoProduto));
             
         }
/**
 * 
 */
function salvaCombo(){
             quantidade = document.getElementById("my_select").value;
             total = parseFloat(localStorage.total);
             $("#linhaTotal").html("R$ "+(((total)+(precoProduto * quantidade))));
             $("#linhaPrecoProduto").html("R$ "+(precoProduto * quantidade));
             //teste2(1,1,3);
         }
         
/**
 * 
 */
function salvaMesa(mesa){
    total = 0;
    localStorage.mesa = mesa;
    window.location.href = 'sever/salva_mesa.php?mesa='+mesa;
}

/**
 * 
 */
function addProduto(){
    if(quantidade == null){
        quantidade = 1;
    }
    $("#header_2").load('sever/add_produto.php',{produto:idProduto, quantidade:quantidade, pedido:localStorage.idPedido});
    localStorage.total = (parseFloat(localStorage.total)+(precoProduto*quantidade));
    teste();
    //$("#dadosMesa").html("Mesa: "+localStorage.mesa+" | Total: R$ "+localStorage.total);
}

function abrirConta(){
    
   // $("#page_cardapio").load('sever/abrir_conta.php',{id:localStorage.idPedido});
    //window.location.href = 'sever/abrir_conta.php?id='+localStorage.idPedido;
    $("#page_conta").load("sever/abrir_conta.php?id="+localStorage.idPedido);
}

function teste(){
    var total = parseFloat(localStorage.total);
    $("#header_0 h1").html("Mesa: "+localStorage.mesa+" Total: R$"+total.toFixed(2));
}

function deleteMe(row_id, precoProd, quantidade){
	if(!confirm('Você tem certeza que deseja deletar este item?')){
	ev.preventDefault();
	return false;
	}else{
            $('#'+row_id).remove();
            $("#header_2").load('sever/deleta_produto.php',{id:row_id});
            localStorage.total = (parseFloat(localStorage.total)-(precoProd*quantidade));
            $("#linhaTotal").html(localStorage.total);
            teste();
            //localStorage.total = (parseFloat(localStorage.total)-(precoP * qtdP))
            //$("#linhaTotal").html("R$ "+localStorage.total);
	}
}

function efetuaPedido(){
    
    if(!confirm('Deseja efetuar esse pedido agora?')){
	ev.preventDefault();
	return false;
	}else{
            var pedido = localStorage.idPedido;
            $("#header_2").load('sever/efetuar_pedido.php?idd='+pedido);
            var rowCount = $('#tabelaConta tr').length;
            var cont = 0;
            while(cont<rowCount){
                $("#botaoDeletar").remove();
                
                cont++;
            }
            $('#tabelaConta tr').removeClass("warning");
            $('#tabelaConta tr').addClass("success");
            
	}
}

