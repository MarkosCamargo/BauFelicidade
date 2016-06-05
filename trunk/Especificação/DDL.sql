DROP TABLE IF EXISTS "Produto" CASCADE
;

CREATE TABLE "Produto"
(
	"produtoId" integer NOT NULL,
	"descricao" varchar,
	"valor" double,
	"categoriaId" integer
)
;

ALTER TABLE "Produto" ADD CONSTRAINT "PK_Produto"
	PRIMARY KEY ("produtoId")
;

ALTER TABLE "Produto" ADD CONSTRAINT "FK_tem_Categoria"
	FOREIGN KEY ("categoriaId") REFERENCES "Categoria" ("categoriaId") ON DELETE No Action ON UPDATE No Action
;
