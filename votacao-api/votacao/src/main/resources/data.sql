insert into tb_associado(nome, cpf) values ('Arthur', '98998987')
insert into tb_pauta(descricao) values ('Redução da taxa de condominio')
insert into tb_assembleia (ativa, data_criacao, descricao, duracao, pauta_id) values (1, now(), 'decidir taxa de condominio', 60, 1)