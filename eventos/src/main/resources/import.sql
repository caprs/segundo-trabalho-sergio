CATEGORIAS

INSERT INTO categoria (id, nome, descricao) VALUES
(1, 'Tecnologia', 'Eventos relacionados à tecnologia e inovação'),
(2, 'Negócios', 'Eventos sobre empreendedorismo e negócios'),
(3, 'Educação', 'Eventos voltados para educação e aprendizagem'),
(4, 'Saúde', 'Eventos relacionados à saúde e bem-estar'),
(5, 'Cultura', 'Eventos relacionados à cultura e arte');


LOCAIS

INSERT INTO local (id, nome, endereco, capacidade) VALUES
(1, 'Auditório Principal', 'Rua das Flores, 100', 500),
(2, 'Centro de Convenções', 'Avenida Brasil, 500', 1000),
(3, 'Sala de Inovação', 'Rua da Tecnologia, 250', 150),
(4, 'Teatro Municipal', 'Praça Central, 50', 800),
(5, 'Espaço Universitário', 'Avenida Universitária, 300', 300);


PALESTRANTES

INSERT INTO palestrante (id, nome, mini_bio, email) VALUES
(1, 'Ana Souza', 'Especialista em tecnologia e desenvolvimento de software', 'ana.souza@email.com'),
(2, 'Carlos Oliveira', 'Empreendedor e consultor de negócios', 'carlos.oliveira@email.com'),
(3, 'Mariana Santos', 'Professora e pesquisadora na área de educação', 'mariana.santos@email.com'),
(4, 'Rafael Costa', 'Profissional da área de saúde e qualidade de vida', 'rafael.costa@email.com'),
(5, 'Juliana Lima', 'Produtora cultural e especialista em eventos', 'juliana.lima@email.com');


PARTICIPANTES

INSERT INTO participante (id, nome, email, telefone) VALUES
(1, 'João Silva', 'joao.silva@email.com', '18999990001'),
(2, 'Maria Oliveira', 'maria.oliveira@email.com', '18999990002'),
(3, 'Pedro Santos', 'pedro.santos@email.com', '18999990003'),
(4, 'Larissa Costa', 'larissa.costa@email.com', '18999990004'),
(5, 'Gabriel Lima', 'gabriel.lima@email.com', '18999990005');


EVENTOS

INSERT INTO evento
(id, nome, descricao, data_inicio, data_fim, capacidade, status, categoria_id, local_id, palestrante_id)
VALUES
(1, 'Tech Summit 2026',
 'Encontro sobre tecnologia, inovação e desenvolvimento de software',
 '2026-10-10 09:00:00',
 '2026-10-10 18:00:00',
 500,
 'ABERTO',
 1,
 1,
 1);

INSERT INTO evento
(id, nome, descricao, data_inicio, data_fim, capacidade, status, categoria_id, local_id, palestrante_id)
VALUES
(2, 'Empreendedorismo na Prática',
 'Evento voltado para empreendedorismo e criação de novos negócios',
 '2026-10-15 14:00:00',
 '2026-10-15 18:00:00',
 1000,
 'ABERTO',
 2,
 2,
 2);

INSERT INTO evento
(id, nome, descricao, data_inicio, data_fim, capacidade, status, categoria_id, local_id, palestrante_id)
VALUES
(3, 'Educação e Futuro',
 'Debate sobre os desafios e oportunidades da educação',
 '2026-10-20 08:00:00',
 '2026-10-20 17:00:00',
 150,
 'ABERTO',
 3,
 3,
 3);

INSERT INTO evento
(id, nome, descricao, data_inicio, data_fim, capacidade, status, categoria_id, local_id, palestrante_id)
VALUES
(4, 'Saúde e Qualidade de Vida',
 'Palestras sobre saúde, prevenção e qualidade de vida',
 '2026-10-25 09:00:00',
 '2026-10-25 16:00:00',
 800,
 'ABERTO',
 4,
 4,
 4);

INSERT INTO evento
(id, nome, descricao, data_inicio, data_fim, capacidade, status, categoria_id, local_id, palestrante_id)
VALUES
(5, 'Festival de Cultura',
 'Evento dedicado à cultura, arte e manifestações culturais',
 '2026-10-30 10:00:00',
 '2026-10-30 20:00:00',
 300,
 'ABERTO',
 5,
 5,
 5);


INSCRIÇÕES

INSERT INTO inscricao
(id, data_inscricao, status, evento_id, participante_id)
VALUES
(1, '2026-09-01 10:00:00', 'CONFIRMADA', 1, 1);

INSERT INTO inscricao
(id, data_inscricao, status, evento_id, participante_id)
VALUES
(2, '2026-09-02 11:30:00', 'CONFIRMADA', 2, 2);

INSERT INTO inscricao
(id, data_inscricao, status, evento_id, participante_id)
VALUES
(3, '2026-09-03 14:00:00', 'CONFIRMADA', 3, 3);

INSERT INTO inscricao
(id, data_inscricao, status, evento_id, participante_id)
VALUES
(4, '2026-09-04 15:30:00', 'PENDENTE', 4, 4);

INSERT INTO inscricao
(id, data_inscricao, status, evento_id, participante_id)
VALUES
(5, '2026-09-05 09:00:00', 'CONFIRMADA', 5, 5);