CREATE database mydb;
USE `mydb` ;

CREATE TABLE IF NOT EXISTS `mydb`.`Item` (
  `idItem` INT NOT NULL AUTO_INCREMENT,
  `nomeItem` VARCHAR(45) NULL,
  `preço` FLOAT NULL,
  `fornecedor` VARCHAR(45) NULL,
  `estoque` FLOAT NULL,
  PRIMARY KEY (`idItem`));


CREATE TABLE IF NOT EXISTS `mydb`.`Cadastro` (
  `cpf` VARCHAR(17) NOT NULL,
  `nome` VARCHAR(45) NULL,
  `senha` VARCHAR(45) NULL,
  `telefone` VARCHAR(45) NULL,
  `nivelAcesso` VARCHAR(45) NULL,
  PRIMARY KEY (`cpf`));


CREATE TABLE IF NOT EXISTS `mydb`.`Vendas` (
  `idVenda` INT NOT NULL AUTO_INCREMENT,
  `cpf` VARCHAR(17) NOT NULL,
  `valor_total` VARCHAR(45) NULL,
  `status_pedido` VARCHAR(45) NULL,
  `Data_venda` DATE NULL,
  PRIMARY KEY (`idVenda`, `cpf`),
  INDEX `fk_Vendas_Cadastro1_idx` (`cpf` ASC) VISIBLE,
  CONSTRAINT `fk_Vendas_Cadastro1`
    FOREIGN KEY (`cpf`)
    REFERENCES `mydb`.`Cadastro` (`cpf`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


CREATE TABLE IF NOT EXISTS `mydb`.`Pedido` (
  `idPedido` INT NOT NULL AUTO_INCREMENT,
  `quantidade` INT NULL,
  `idItem` INT NOT NULL,
  `idVenda` INT NOT NULL,
  PRIMARY KEY (`idPedido`, `idItem`, `idVenda`),
  INDEX `fk_Pedido_Item1_idx` (`idItem` ASC) VISIBLE,
  INDEX `fk_Pedido_Vendas1_idx` (`idVenda` ASC) VISIBLE,
  CONSTRAINT `fk_Pedido_Item1`
    FOREIGN KEY (`idItem`)
    REFERENCES `mydb`.`Item` (`idItem`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Pedido_Vendas1`
    FOREIGN KEY (`idVenda`)
    REFERENCES `mydb`.`Vendas` (`idVenda`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


CREATE TABLE IF NOT EXISTS `mydb`.`Total_Item` (
  `idTotal_Item` INT NOT NULL AUTO_INCREMENT,
  `valor_total` FLOAT NULL,
  `idPedido` INT NOT NULL,
  `idItem` INT NOT NULL,
  PRIMARY KEY (`idTotal_Item`, `idPedido`, `idItem`),
  INDEX `fk_Total_Item_Pedido1_idx` (`idPedido` ASC, `idItem` ASC) VISIBLE,
  CONSTRAINT `fk_Total_Item_Pedido1`
    FOREIGN KEY (`idPedido` , `idItem`)
    REFERENCES `mydb`.`Pedido` (`idPedido` , `idItem`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);



insert into item (nomeItem,preço,fornecedor ,estoque) values ("P_BatataFrita",25.00,"Ze_Batata",15);
insert into item (nomeItem,preço,fornecedor ,estoque) values ("X_Salada",23.00,"Ze_Lanche",12);
insert into item (nomeItem,preço,fornecedor ,estoque) values ("Porcao_Camarao",40.00,"Ze_Camarao",10);
insert into item (nomeItem,preço,fornecedor ,estoque) values ("Hamburguer_Soja",35.00,"Ze_Soja",23);
insert into item (nomeItem,preço,fornecedor ,estoque) values ("Onion",25.00,"Ze_Onion",12);
insert into item (nomeItem,preço,fornecedor,estoque ) values ("Refri_Lata",7.00,"Ze_Refri",20);


insert into cadastro (cpf,nome,senha,telefone,nivelAcesso) values ("435.828.098-62","Zezinho ","@f134754","19982743581","Usuario");
insert into cadastro (cpf,nome,senha,telefone,nivelAcesso) values ("222.334.030-02","Mariazinha ","@f134758","19982743585","Usuario");
insert into cadastro (cpf,nome,senha,telefone,nivelAcesso) values ("371.812.920-56","Master Zezinho ","@f112233","19982743581","Gerente");



insert into vendas  (cpf,valor_total,status_pedido,Data_venda) values ("435.828.098-62",0,"Não Pago",now());

-- -------------------------------------------------------------------------------------------------------
DELIMITER $$
create trigger insertVenda
after insert
on pedido
for each row
begin

 declare valor_item float;
 declare quant_item int;
 declare valorTotall float; 

select item.preço into valor_item from item where idItem = new.idItem;
select quantidade into quant_item from pedido where idPedido = new.idPedido;

set valorTotall = valor_item * quant_item;

SET SQL_SAFE_UPDATES = 0;
update vendas set valor_total = valor_total + valorTotall where vendas.status_pedido = "Não Pago";
update item set estoque = estoque - new.quantidade where idItem = new.idItem;
SET SQL_SAFE_UPDATES = 1;

end
$$

-- ----------------------------------------------------------------------------------------------------
DELIMITER $$
create trigger insertPedido
after insert
on pedido
for each row
begin

 declare valor_item float;
 declare quant_item int;
 declare valorTotall float; 

select item.preço into valor_item from item where idItem = new.idItem;
select quantidade into quant_item from pedido where idPedido = new.idPedido;

set valorTotall = valor_item * quant_item;

insert into total_item (valor_total,idPedido,idItem) values (valorTotall,new.idPedido,new.idItem);

ALTER TABLE vendas CHANGE valor_total valor_total DECIMAL(7,2);
end
$$

-- ------------------------------------------------------------------------------------------------------------------------
DELIMITER || 
	create function calcular_10 (id_vendaa int) 
	
    returns float deterministic
    begin 
    declare nro_valor float;
    declare nro float;
    select valor_total into nro_valor from vendas where idVenda = id_vendaa;
    
    set nro = nro_valor * 0.1;
    SET SQL_SAFE_UPDATES = 0;
    update vendas set status_pedido = "Pago" where idVenda = id_vendaa;
     update vendas set eco_10 = nro where idVenda = id_vendaa;
    SET SQL_SAFE_UPDATES = 1;

	return nro;
    end 
    || 
    DELIMITER ;

-- -----------------------------------------------------------------------------------------------------------------
create procedure ver_comanda( id_vendaa int) 
select item.nomeItem,item.preço ,pedido.quantidade,total_item.valor_total,vendas.idVenda
 from item inner join pedido using (idItem) inner join
total_item using (idPedido) inner join vendas using(idVenda) where idVenda = id_vendaa;

call ver_comanda(6);

-- -------------------------------------------------------------------------------------------------------------
create procedure ver_receita_dia(data_e date) 
select item.nomeItem,item.preço ,pedido.quantidade,total_item.valor_total,vendas.idVenda
from item inner join pedido using (idItem) inner join
total_item using (idPedido) inner join vendas using(idVenda) where Data_venda = data_e;

call ver_receita_dia("2022-06-16");

-- -------------------------------------------------------------------------------------------------------------

DELIMITER $$
create function verValorTotal (id_vendaa int)
returns integer deterministic
begin
declare nro integer;
select valor_Total into nro from vendas where idVenda = id_vendaa;

return nro;
end $$
DELIMITER ;

select verValorTotal(14);

-- ------------------------------------------------------------------------------------------------

create view totalReceita as select sum(valor_total) 
from vendas where Data_venda = "2022-06-16";

select * from  totalReceita;

-- -------------------------------------------------------------------------------------------------

DELIMITER $$
create procedure venda_item (nome_itemm varchar (17))
begin 
Declare vv float default 0; 
Declare somaTotal float default 0;
Declare fimloop int default 0;
declare meucursor cursor for select pedido.quantidade from pedido 
inner join item using (idItem) where nomeItem = nome_itemm;
declare continue handler for not found set fimloop =1;

open meucursor ; 
while (fimloop !=1) do
set somaTotal = somaTotal + vv; 
fetch meucursor into vv;
end while; 
select somaTotal;
end $$;
DELIMITER ;

call venda_item ("Refri_Lata");

select * from item;
select * from pedido;

-- ------------------------------------------------------------------------------------------------------------

DELIMITER $$
create function desconto (id_vendaa int)
returns float deterministic
begin
declare nro float;
declare nroDesconto float;

select valor_Total into nro from vendas where idVenda = id_vendaa;
set nroDesconto = nro * 0.05 ;


update vendas set valor_total = valor_total - nroDesconto where idVenda = id_vendaa;

return nroDesconto;
end $$
DELIMITER ;

select desconto (24);

 
