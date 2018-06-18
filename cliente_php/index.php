<?php
# Informa qual o conjunto de caracteres será usado.
header('Content-Type: text/html; charset=utf-8');

//chama o arquivo de conexão com o banco de dados
require 'sever/conexao.php';

//seleciona os dados da tabela categoria
$query = mysql_query("SELECT * FROM categoria");

//seleciona mesa
$queryMesa = mysql_query("SELECT * FROM mesas");

?>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-type" content="text/html; charset=utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
        <meta name="apple-mobile-web-app-capable" content="yes" />
        <meta http-equiv="pragma" content="no-cache" />
        <script type="text/javascript" charset="utf-8" src="http://localhost:58888/_appMobi/appmobi.js"></script>

        <script type="text/javascript" language="javascript">
            // This event handler is fired once the AppMobi libraries are ready
            function onDeviceReady() {
                //use AppMobi viewport to handle device resolution differences if you want
                //AppMobi.display.useViewport(768,1024);

                //hide splash screen now that our app is ready to run
                AppMobi.device.hideSplashScreen();
                setTimeout(function(){
                    $.ui.launch();
                },50);
            }
            //initial event handler to detect when appMobi is ready to roll
            document.addEventListener("appMobi.device.ready",onDeviceReady,false);
        </script> 

        <script src="js/appframework.ui.min.js"></script>
        <script src="js/jquery-1.10.2.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script src="js/funcoes.js"></script>

        <script>
            $.ui.autoLaunch=false;
            $.ui.useOSThemes=false;
            $.ui.blockPageScroll();
            $(document).ready(function(){
                if($.ui.useOSThemes&&!$.os.ios&&$("#afui").get(0).className!=="ios")
                    $("#afui").removeClass("ios");
            });
        </script>

        <link href="css/bootstrap.css" rel="stylesheet" type="text/css">
        <link href="css/icons.css" rel="stylesheet" type="text/css">
        <link href="css/af.ui.css" rel="stylesheet" type="text/css">
    </head>
    <body style="width: 100%; margin-left: 0px; padding-left: 0px;">
        <div id="afui" class='ios'>

            <div id="header"></div>

            <div id="content">
                
                <div class="panel" title="select_mesa" data-footer="footer_0" id="page_select" style="" data-appbuilder-object="page" data-header="header_1">
                    <div class="featurette">
                        <img src="img/logotipo_restaurante_logomarca_lanchonete.jpg">
                        <h2 class="featurette-heading">Selecione a Mesa:</h2>
                        <select id="selecionaMesa">
                            <?php while($prod = mysql_fetch_array($queryMesa)) { ?>
                            <option  value="<?php echo $prod['numero'] ?>"><?php echo $prod['numero'] ?></option>
                            <?php } ?>
                        </select>
                        <a onclick="salvaMesa(document.getElementById('selecionaMesa').value);" class="btn-large btn-primary">Avançar<i class="icon-arrow-right icon-white"></i></a>
                    </div>
                </div>
                
                <div class="panel" title="Main" data-footer="footer_0" id="page_0" selected="selected" style="background: url(img/bck.jpg);" data-appbuilder-object="page" data-header="header_0">
                    <br>
                    <img src="img/publicidade.jpg" class="img-polaroid" style="width: 100%; height: 40%">
                    
                </div>
                <div class="panel" title="Cardapio" data-footer="footer_0" id="page_cardapio" data-appbuilder-object="page" style="" data-header="header_0">
                    <ul class="list" data-appbuilder-object="list" data-position="static">
                            <?php
                            while ($prod = mysql_fetch_array($query)) {
                             ?>
                            <li>
                                <a href="sever/abrir_sub_categoria.php?acc=<?php echo $prod['ID'] ?>" style="font-size: 1.9em;"><img src="img/categoria/<?php echo $prod['imagem_categoria'];?>" style="height: 30%; width: 40%;">  <?php echo $prod['nome'] ?></a>
                            </li>
                            <?php
                            }
                            ?>
                    </ul>
                </div>
                
                <div class="panel" title="Conta" data-footer="footer_0" id="page_conta" data-appbuilder-object="page" style="" data-header="header_0">
                    
                </div>
            </div>
            <!--Modal adiciona ao pedido -->
            <div id="myModal" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                    <h3 id="myModalLabel">Adicionar ao Pedido</h3>
                </div>
                <div class="modal-body">
                    <table class="table">
                        <thead>
                            <tr>
                                <td>Qtd</td>
                                <td>Produto</td>
                                <td>Preço</td>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td><select id="my_select" style="width: 4.2em; height: 3.5em;" onchange="salvaCombo();">
                                        <option value="01">01</option>
                                        <option value="02">02</option>
                                        <option value="03">03</option>
                                        <option value="04">04</option>
                                        <option value="05">05</option>
                                        <option value="06">06</option>
                                        <option value="07">07</option>
                                        <option value="08">08</option>
                                        <option value="09">09</option>
                                        <option value="10">10</option>
                                    </select></td>
                                <td id="linhaNomeProduto"></td>
                                <td id="linhaPrecoProduto"></td>
                            </tr>
                        </tbody>
                        <tfoot>
                            <tr>
                                <td>Total:</td>
                                <td></td>
                                <td id="linhaTotal"></td>
                            </tr>
                        </tfoot>
                    </table>
                </div>
                <div class="modal-footer">
                    <button onclick="addProduto();" class="btn-large btn-primary" data-dismiss="modal" aria-hidden="true"><i class="icon-ok icon-white"></i> Adicionar ao Pedido</button>
                    <button class="btn-large btn-danger" data-dismiss="modal" aria-hidden="true"><i class="icon-remove icon-white"></i> Cancelar</button>
                </div>
            </div> 
            <!--fim modal -->

            <div id="navbar">
                <a href="#page_0" class="icon home" style="">Menu</a><a href="#page_cardapio" class="icon paper" style="">Cardápio</a><a href="#page_conta" class="icon basket" style="">Conta</a><a href="#page_0" class="icon check" style="">Pedir</a><a href="#page_0" class="icon add" style="">Pagar</a>
            </div>

            <footer id="footer_0" data-appbuilder-object="footer">
                <a href="#page_0" class="icon home">Inicio</a><a href="#page_cardapio" class="icon paper">Cardápio</a><a onclick="abrirConta();" href="#page_conta" class="icon basket">Conta</a><a onclick="efetuaPedido();" class="icon check">Pedir</a><a href="#page_select" onclick="alert('Aguarde, sua conta será entregue na mesa\n obrigado pela preferencia\n Volte Sempre!');" class="icon add">Pagar</a>
            </footer>
            <header id="header_0" data-appbuilder-object="header">
                <a id="backButton" href="#" class="button back" style="visibility: visible; ">Voltar</a>
                <h1 id="pageTitle"><script>teste();</script></h1>
            </header>
            <header id="header_1" data-appbuilder-object="header">
                <h1 class="">Bem Vindo !</h1>
            </header>
            <header id="header_2" data-appbuilder-object="header">
                <a id="backButton" href="#" class="button back" style="visibility: visible; ">Voltar</a>
                <h1 id="pageTitle"></h1>
            </header>
        </div>
    </body>
</html>