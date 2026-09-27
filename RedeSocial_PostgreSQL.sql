-- ============================================================
-- Projeto Rede Social - sincronização idempotente para PostgreSQL
-- Compatível com o banco já existente no pgAdmin4.
-- ============================================================

CREATE TABLE IF NOT EXISTS usuarios (
    id                  SERIAL       PRIMARY KEY,
    nome                VARCHAR(100) NOT NULL,
    login               VARCHAR(150) NOT NULL,
    senha               VARCHAR(255) NOT NULL,
    caminho_foto_perfil VARCHAR(255),
    biografia           TEXT
);

CREATE UNIQUE INDEX IF NOT EXISTS ux_usuarios_login
    ON usuarios (login);

CREATE TABLE IF NOT EXISTS fotos (
    id              SERIAL       PRIMARY KEY,
    id_usuario      INTEGER      NOT NULL,
    legenda         TEXT,
    caminho_arquivo VARCHAR(255) NOT NULL,
    data_postagem   TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS comentarios (
    id              SERIAL    PRIMARY KEY,
    id_usuario      INTEGER   NOT NULL,
    id_foto         INTEGER   NOT NULL,
    conteudo        TEXT      NOT NULL,
    data_comentario TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS mensagens (
    id              SERIAL    PRIMARY KEY,
    id_remetente    INTEGER   NOT NULL,
    id_destinatario INTEGER   NOT NULL,
    conteudo        TEXT      NOT NULL,
    data_envio      TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT ck_mensagem_usuarios_distintos
        CHECK (id_remetente <> id_destinatario)
);

CREATE TABLE IF NOT EXISTS curtidas (
    id_usuario INTEGER NOT NULL,
    id_foto    INTEGER NOT NULL,
    PRIMARY KEY (id_usuario, id_foto)
);

CREATE TABLE IF NOT EXISTS compartilhamentos (
    id_usuario INTEGER NOT NULL,
    id_foto    INTEGER NOT NULL,
    PRIMARY KEY (id_usuario, id_foto)
);

CREATE TABLE IF NOT EXISTS seguidores (
    id_seguidor INTEGER NOT NULL,
    id_seguido  INTEGER NOT NULL,
    PRIMARY KEY (id_seguidor, id_seguido),
    CONSTRAINT ck_nao_seguir_a_si_mesmo CHECK (id_seguidor <> id_seguido)
);

CREATE TABLE IF NOT EXISTS hashtags (
    id    SERIAL       PRIMARY KEY,
    texto VARCHAR(50)  NOT NULL
);

CREATE UNIQUE INDEX IF NOT EXISTS ux_hashtags_texto
    ON hashtags (texto);

CREATE TABLE IF NOT EXISTS fotos_hashtags (
    id_foto    INTEGER NOT NULL,
    id_hashtag INTEGER NOT NULL,
    PRIMARY KEY (id_foto, id_hashtag)
);

-- Chaves estrangeiras
DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM pg_constraint
        WHERE conname = 'fotos_id_usuario_fkey'
    ) THEN
        ALTER TABLE fotos
            ADD CONSTRAINT fotos_id_usuario_fkey
            FOREIGN KEY (id_usuario) REFERENCES usuarios(id) ON DELETE CASCADE;
    END IF;
END $$;

DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM pg_constraint
        WHERE conname = 'comentarios_id_usuario_fkey'
    ) THEN
        ALTER TABLE comentarios
            ADD CONSTRAINT comentarios_id_usuario_fkey
            FOREIGN KEY (id_usuario) REFERENCES usuarios(id) ON DELETE CASCADE;
    END IF;
END $$;

DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM pg_constraint
        WHERE conname = 'comentarios_id_foto_fkey'
    ) THEN
        ALTER TABLE comentarios
            ADD CONSTRAINT comentarios_id_foto_fkey
            FOREIGN KEY (id_foto) REFERENCES fotos(id) ON DELETE CASCADE;
    END IF;
END $$;

DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM pg_constraint
        WHERE conname = 'mensagens_id_remetente_fkey'
    ) THEN
        ALTER TABLE mensagens
            ADD CONSTRAINT mensagens_id_remetente_fkey
            FOREIGN KEY (id_remetente) REFERENCES usuarios(id) ON DELETE CASCADE;
    END IF;
END $$;

DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM pg_constraint
        WHERE conname = 'mensagens_id_destinatario_fkey'
    ) THEN
        ALTER TABLE mensagens
            ADD CONSTRAINT mensagens_id_destinatario_fkey
            FOREIGN KEY (id_destinatario) REFERENCES usuarios(id) ON DELETE CASCADE;
    END IF;
END $$;

DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM pg_constraint
        WHERE conname = 'curtidas_id_usuario_fkey'
    ) THEN
        ALTER TABLE curtidas
            ADD CONSTRAINT curtidas_id_usuario_fkey
            FOREIGN KEY (id_usuario) REFERENCES usuarios(id) ON DELETE CASCADE;
    END IF;
END $$;

DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM pg_constraint
        WHERE conname = 'curtidas_id_foto_fkey'
    ) THEN
        ALTER TABLE curtidas
            ADD CONSTRAINT curtidas_id_foto_fkey
            FOREIGN KEY (id_foto) REFERENCES fotos(id) ON DELETE CASCADE;
    END IF;
END $$;

DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM pg_constraint
        WHERE conname = 'compartilhamentos_id_usuario_fkey'
    ) THEN
        ALTER TABLE compartilhamentos
            ADD CONSTRAINT compartilhamentos_id_usuario_fkey
            FOREIGN KEY (id_usuario) REFERENCES usuarios(id) ON DELETE CASCADE;
    END IF;
END $$;

DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM pg_constraint
        WHERE conname = 'compartilhamentos_id_foto_fkey'
    ) THEN
        ALTER TABLE compartilhamentos
            ADD CONSTRAINT compartilhamentos_id_foto_fkey
            FOREIGN KEY (id_foto) REFERENCES fotos(id) ON DELETE CASCADE;
    END IF;
END $$;

DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM pg_constraint
        WHERE conname = 'seguidores_id_seguidor_fkey'
    ) THEN
        ALTER TABLE seguidores
            ADD CONSTRAINT seguidores_id_seguidor_fkey
            FOREIGN KEY (id_seguidor) REFERENCES usuarios(id) ON DELETE CASCADE;
    END IF;
END $$;

DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM pg_constraint
        WHERE conname = 'seguidores_id_seguido_fkey'
    ) THEN
        ALTER TABLE seguidores
            ADD CONSTRAINT seguidores_id_seguido_fkey
            FOREIGN KEY (id_seguido) REFERENCES usuarios(id) ON DELETE CASCADE;
    END IF;
END $$;

DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM pg_constraint
        WHERE conname = 'fotos_hashtags_id_foto_fkey'
    ) THEN
        ALTER TABLE fotos_hashtags
            ADD CONSTRAINT fotos_hashtags_id_foto_fkey
            FOREIGN KEY (id_foto) REFERENCES fotos(id) ON DELETE CASCADE;
    END IF;
END $$;

DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM pg_constraint
        WHERE conname = 'fotos_hashtags_id_hashtag_fkey'
    ) THEN
        ALTER TABLE fotos_hashtags
            ADD CONSTRAINT fotos_hashtags_id_hashtag_fkey
            FOREIGN KEY (id_hashtag) REFERENCES hashtags(id) ON DELETE CASCADE;
    END IF;
END $$;

-- Índices de consulta
CREATE INDEX IF NOT EXISTS idx_fotos_usuario
    ON fotos (id_usuario, data_postagem DESC);

CREATE INDEX IF NOT EXISTS idx_comentarios_foto
    ON comentarios (id_foto, data_comentario);

CREATE INDEX IF NOT EXISTS idx_mensagens_conversa
    ON mensagens (id_remetente, id_destinatario, data_envio);

CREATE INDEX IF NOT EXISTS idx_seguidores_seguido
    ON seguidores (id_seguido);
