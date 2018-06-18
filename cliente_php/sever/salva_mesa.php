<?php

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

    require 'conexao.php';
    $mesa = $_GET['mesa'];
    mysql_query("INSERT INTO pedido (ID_mesa) VALUES($mesa);") or die(mysql_error());
     
    $pedido = mysql_insert_id();
?>
<script>
    localStorage.idPedido = <?php echo $pedido;?>;
    localStorage.mesa = "<?php echo $mesa;?>";
    localStorage.total = "00.00";
    window.location.href = '../index.php';
</script>
