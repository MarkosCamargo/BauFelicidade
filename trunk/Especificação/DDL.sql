DROP TABLE IF EXISTS "Categoria" CASCADE
;

DROP TABLE IF EXISTS "Cesta" CASCADE
;

DROP TABLE IF EXISTS "Cliente" CASCADE
;

DROP TABLE IF EXISTS "Fornecedor" CASCADE
;

DROP TABLE IF EXISTS "Produto" CASCADE
;

DROP TABLE IF EXISTS "contem" CASCADE
;

DROP TABLE IF EXISTS "lanca" CASCADE
;

DROP TABLE IF EXISTS "avalia" CASCADE
;

CREATE TABLE "Categoria"
(
	"categoriaId" integer NOT NULL,
	"descricao" varchar
)
;

CREATE TABLE "Cesta"
(
	"cestaId" integer NOT NULL,
	"data" date,
	"tema" varchar,
	"valorMaximo" double precision
)
;

CREATE TABLE "Cliente"
(
	"clienteId" integer NOT NULL
)
;

CREATE TABLE "Fornecedor"
(
	"fornecedorId" integer NOT NULL
)
;

CREATE TABLE "Produto"
(
	"produtoId" integer NOT NULL,
	"descricao" varchar,
	"valor" double precision,
	"peso" double precision,
	"categoriaId" integer
)
;

CREATE TABLE "contem"
(
	"dataContido" date,
	"cestaId" integer,
	"produtoId" integer NOT NULL
)
;

CREATE TABLE "lanca"
(
	"valor" double precision,
	"fornecedorId" integer NOT NULL,
	"produtoId" integer NOT NULL
)
;

CREATE TABLE "avalia"
(
	"satisfacao" double precision,
	"produtoId" integer NOT NULL,
	"clienteId" integer
)
;

ALTER TABLE "Categoria" ADD CONSTRAINT "PK_Categoria"
	PRIMARY KEY ("categoriaId")
;

ALTER TABLE "Cesta" ADD CONSTRAINT "PK_Cesta"
	PRIMARY KEY ("cestaId")
;

ALTER TABLE "Cliente" ADD CONSTRAINT "PK_Cliente"
	PRIMARY KEY ("clienteId")
;

ALTER TABLE "Fornecedor" ADD CONSTRAINT "PK_Fornecedor"
	PRIMARY KEY ("fornecedorId")
;

ALTER TABLE "Produto" ADD CONSTRAINT "PK_Produto"
	PRIMARY KEY ("produtoId")
;

ALTER TABLE "Produto" ADD CONSTRAINT "FK_tem_Categoria"
	FOREIGN KEY ("categoriaId") REFERENCES "Categoria" ("categoriaId") ON DELETE No Action ON UPDATE No Action
;

ALTER TABLE "contem" ADD CONSTRAINT "FK_Cesta_contem"
	FOREIGN KEY ("cestaId") REFERENCES "Cesta" ("cestaId") ON DELETE No Action ON UPDATE No Action
;

ALTER TABLE "contem" ADD CONSTRAINT "FK_Produto_contem"
	FOREIGN KEY ("produtoId") REFERENCES "Produto" ("produtoId") ON DELETE No Action ON UPDATE No Action
;

ALTER TABLE "lanca" ADD CONSTRAINT "FK_Fornecedor_lanca"
	FOREIGN KEY ("fornecedorId") REFERENCES "Fornecedor" ("fornecedorId") ON DELETE No Action ON UPDATE No Action
;

ALTER TABLE "lanca" ADD CONSTRAINT "FK_Produto_lanca"
	FOREIGN KEY ("produtoId") REFERENCES "Produto" ("produtoId") ON DELETE No Action ON UPDATE No Action
;

ALTER TABLE "avalia" ADD CONSTRAINT "FK_Produto_avalia"
	FOREIGN KEY ("produtoId") REFERENCES "Produto" ("produtoId") ON DELETE No Action ON UPDATE No Action
;

ALTER TABLE "avalia" ADD CONSTRAINT "FK_Cliente_avalia"
	FOREIGN KEY ("clienteId") REFERENCES "Cliente" ("clienteId") ON DELETE No Action ON UPDATE No Action
;
