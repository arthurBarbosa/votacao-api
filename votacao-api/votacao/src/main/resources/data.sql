insert into tb_associate(ACD_NAME, ACD_CPF) values ('Arthur', '42297294050')
insert into tb_schedule(SCH_DESCRIPTION) values ('Redução da taxa de condominio')
insert into tb_session(SES_ID, SES_DURATION, SES_ISOPEN,SES_SCH_ID) values (null, 60, 1, 1)
insert into tb_votation(VOT_ID, VOT_VOTE, VOT_ACD_ID, VOT_SES_ID) values (null, 1, 1, 1)
