CREATE SCHEMA IF NOT EXISTS NekiSkills;

CREATE SEQUENCE NekiSkills.skill_seq;

CREATE SEQUENCE NekiSkills.usuario_seq;

CREATE SEQUENCE NekiSkills.usuario_skill_seq;

CREATE  TABLE NekiSkills.skill (
	id                   integer  NOT NULL ,
	name                 varchar(100)  NOT NULL ,
	"version"            varchar(10)   ,
	description          varchar(255)  NOT NULL ,
	image_url            varchar(255)   ,
	CONSTRAINT pk_skill_id PRIMARY KEY ( id )
);

CREATE  TABLE NekiSkills."usuario" (
	id                   integer  NOT NULL ,
	login                varchar(12)  NOT NULL ,
	"password"           varchar(100)  NOT NULL ,
	last_login_date      date   ,
	CONSTRAINT pk_tbl_id PRIMARY KEY ( id )
);

CREATE  TABLE NekiSkills.user_skill (
	id                   integer  NOT NULL ,
	user_id              integer  NOT NULL ,
	skill_id             integer  NOT NULL ,
	knowledge_level      integer  NOT NULL ,
	created_at           date  NOT NULL ,
	updated_at           date   ,
	CONSTRAINT pk_user_skill_id PRIMARY KEY ( id )
);

COMMENT ON COLUMN NekiSkills.user_skill.knowledge_level IS 'de 1 a 10';

ALTER TABLE NekiSkills.user_skill ADD CONSTRAINT fk_usua_skill_usuario FOREIGN KEY ( usuario_id ) REFERENCES NekiSkills."usuario"( id );

ALTER TABLE NekiSkills.user_skill ADD CONSTRAINT fk_usua_skill_skill FOREIGN KEY ( skill_id ) REFERENCES NekiSkills.skill( id );
