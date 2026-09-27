-- ============================================================
-- Validação do schema do banco Rede Social no pgAdmin4
-- Verifica se o banco está compatível com os DAOs do projeto
-- ============================================================

-- 1) Tabelas esperadas
SELECT table_name
FROM information_schema.tables
WHERE table_schema = 'public'
  AND table_name IN (
      'usuarios', 'fotos', 'comentarios', 'mensagens',
      'curtidas', 'compartilhamentos', 'seguidores',
      'hashtags', 'fotos_hashtags'
  )
ORDER BY table_name;

-- 2) Tabelas faltando
SELECT unnest(ARRAY[
    'usuarios', 'fotos', 'comentarios', 'mensagens',
    'curtidas', 'compartilhamentos', 'seguidores',
    'hashtags', 'fotos_hashtags'
]) AS tabela_faltando
WHERE unnest NOT IN (
    SELECT table_name
    FROM information_schema.tables
    WHERE table_schema = 'public'
);

-- 3) Colunas esperadas por tabela
SELECT table_name, column_name, data_type
FROM information_schema.columns
WHERE table_schema = 'public'
  AND table_name IN ('usuarios', 'fotos', 'comentarios', 'mensagens', 'curtidas', 'compartilhamentos', 'seguidores', 'hashtags', 'fotos_hashtags')
ORDER BY table_name, column_name;

-- 4) Verificar se login é único
SELECT login, COUNT(*)
FROM usuarios
GROUP BY login
HAVING COUNT(*) > 1;

-- 5) Verificar chaves estrangeiras
SELECT conname AS foreign_key_name,
       conrelid::regclass AS tabela,
       pg_get_constraintdef(oid) AS definicao
FROM pg_constraint
WHERE contype = 'f'
ORDER BY conname;

-- 6) Verificar índices esperados
SELECT indexname, tablename
FROM pg_indexes
WHERE schemaname = 'public'
  AND indexname IN (
      'idx_fotos_usuario',
      'idx_comentarios_foto',
      'idx_mensagens_conversa',
      'idx_seguidores_seguido',
      'ux_usuarios_login',
      'ux_hashtags_texto'
  )
ORDER BY indexname;

-- 7) Verificar se o banco está pronto para a aplicação
SELECT 'usuarios' AS tabela,
       EXISTS (SELECT 1 FROM information_schema.columns WHERE table_name = 'usuarios') AS existe,
       EXISTS (SELECT 1 FROM information_schema.columns WHERE table_name = 'usuarios' AND column_name = 'login') AS tem_login,
       EXISTS (SELECT 1 FROM information_schema.columns WHERE table_name = 'usuarios' AND column_name = 'senha') AS tem_senha;

SELECT 'fotos' AS tabela,
       EXISTS (SELECT 1 FROM information_schema.columns WHERE table_name = 'fotos') AS existe,
       EXISTS (SELECT 1 FROM information_schema.columns WHERE table_name = 'fotos' AND column_name = 'id_usuario') AS tem_id_usuario,
       EXISTS (SELECT 1 FROM information_schema.columns WHERE table_name = 'fotos' AND column_name = 'caminho_arquivo') AS tem_caminho_arquivo;

SELECT 'comentarios' AS tabela,
       EXISTS (SELECT 1 FROM information_schema.columns WHERE table_name = 'comentarios') AS existe,
       EXISTS (SELECT 1 FROM information_schema.columns WHERE table_name = 'comentarios' AND column_name = 'id_foto') AS tem_id_foto,
       EXISTS (SELECT 1 FROM information_schema.columns WHERE table_name = 'comentarios' AND column_name = 'conteudo') AS tem_conteudo;

SELECT 'mensagens' AS tabela,
       EXISTS (SELECT 1 FROM information_schema.columns WHERE table_name = 'mensagens') AS existe,
       EXISTS (SELECT 1 FROM information_schema.columns WHERE table_name = 'mensagens' AND column_name = 'id_remetente') AS tem_remetente,
       EXISTS (SELECT 1 FROM information_schema.columns WHERE table_name = 'mensagens' AND column_name = 'id_destinatario') AS tem_destinatario;
