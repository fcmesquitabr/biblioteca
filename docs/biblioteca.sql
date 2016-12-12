--
-- PostgreSQL database dump
--

-- Dumped from database version 9.5.3
-- Dumped by pg_dump version 9.5.1

-- Started on 2016-12-11 20:57:02

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 2248 (class 1262 OID 16404)
-- Name: biblioteca; Type: DATABASE; Schema: -; Owner: postgres
--

--CREATE DATABASE biblioteca WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'Portuguese_Brazil.1252' LC_CTYPE = 'Portuguese_Brazil.1252';


--ALTER DATABASE biblioteca OWNER TO postgres;

--\connect biblioteca

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 1 (class 3079 OID 12355)
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- TOC entry 2251 (class 0 OID 0)
-- Dependencies: 1
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 182 (class 1259 OID 16418)
-- Name: aluno; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE aluno (
    id integer NOT NULL,
    nome character varying(100) NOT NULL
);


ALTER TABLE aluno OWNER TO postgres;

--
-- TOC entry 181 (class 1259 OID 16416)
-- Name: aluno_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE aluno_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE aluno_id_seq OWNER TO postgres;

--
-- TOC entry 2252 (class 0 OID 0)
-- Dependencies: 181
-- Name: aluno_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE aluno_id_seq OWNED BY aluno.id;


--
-- TOC entry 186 (class 1259 OID 16434)
-- Name: autor; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE autor (
    id integer NOT NULL,
    nome character varying(100) NOT NULL
);


ALTER TABLE autor OWNER TO postgres;

--
-- TOC entry 185 (class 1259 OID 16432)
-- Name: autor_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE autor_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE autor_id_seq OWNER TO postgres;

--
-- TOC entry 2253 (class 0 OID 0)
-- Dependencies: 185
-- Name: autor_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE autor_id_seq OWNED BY autor.id;


--
-- TOC entry 199 (class 1259 OID 16539)
-- Name: distribuidora; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE distribuidora (
    id integer NOT NULL,
    nome character varying(100) NOT NULL
);


ALTER TABLE distribuidora OWNER TO postgres;

--
-- TOC entry 198 (class 1259 OID 16537)
-- Name: distribuidora_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE distribuidora_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE distribuidora_id_seq OWNER TO postgres;

--
-- TOC entry 2254 (class 0 OID 0)
-- Dependencies: 198
-- Name: distribuidora_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE distribuidora_id_seq OWNED BY distribuidora.id;


--
-- TOC entry 184 (class 1259 OID 16426)
-- Name: editora; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE editora (
    id integer NOT NULL,
    nome character varying(100)
);


ALTER TABLE editora OWNER TO postgres;

--
-- TOC entry 183 (class 1259 OID 16424)
-- Name: editora_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE editora_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE editora_id_seq OWNER TO postgres;

--
-- TOC entry 2255 (class 0 OID 0)
-- Dependencies: 183
-- Name: editora_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE editora_id_seq OWNED BY editora.id;


--
-- TOC entry 188 (class 1259 OID 16442)
-- Name: livro; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE livro (
    id integer NOT NULL,
    titulo character varying(200) NOT NULL,
    isbn character varying(20) NOT NULL,
    descricao text,
    editoraid integer NOT NULL,
    edicao character varying(50),
    quantidade integer,
    quantidadedisponivel integer
);


ALTER TABLE livro OWNER TO postgres;

--
-- TOC entry 187 (class 1259 OID 16440)
-- Name: livro_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE livro_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE livro_id_seq OWNER TO postgres;

--
-- TOC entry 2256 (class 0 OID 0)
-- Dependencies: 187
-- Name: livro_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE livro_id_seq OWNED BY livro.id;


--
-- TOC entry 189 (class 1259 OID 16456)
-- Name: livroautor; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE livroautor (
    autorid integer NOT NULL,
    livroid integer NOT NULL
);


ALTER TABLE livroautor OWNER TO postgres;

--
-- TOC entry 195 (class 1259 OID 16512)
-- Name: pedido; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE pedido (
    id integer NOT NULL,
    datarealizacao time without time zone NOT NULL,
    distribuidoraid integer,
    descricao character varying,
    situacaopedidoid integer
);


ALTER TABLE pedido OWNER TO postgres;

--
-- TOC entry 194 (class 1259 OID 16510)
-- Name: pedido_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE pedido_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE pedido_id_seq OWNER TO postgres;

--
-- TOC entry 2257 (class 0 OID 0)
-- Dependencies: 194
-- Name: pedido_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE pedido_id_seq OWNED BY pedido.id;


--
-- TOC entry 197 (class 1259 OID 16520)
-- Name: pedidolivro; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE pedidolivro (
    id integer NOT NULL,
    pedidoid integer NOT NULL,
    livroid integer NOT NULL,
    quantidadesolicitada integer NOT NULL,
    quantidadeconfirmada integer
);


ALTER TABLE pedidolivro OWNER TO postgres;

--
-- TOC entry 196 (class 1259 OID 16518)
-- Name: pedidolivro_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE pedidolivro_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE pedidolivro_id_seq OWNER TO postgres;

--
-- TOC entry 2258 (class 0 OID 0)
-- Dependencies: 196
-- Name: pedidolivro_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE pedidolivro_id_seq OWNED BY pedidolivro.id;


--
-- TOC entry 191 (class 1259 OID 16473)
-- Name: reservalivro; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE reservalivro (
    id integer NOT NULL,
    alunoid integer,
    livroid integer NOT NULL,
    situacaoreservaid integer,
    datarealizacao timestamp without time zone,
    dataemprestimo timestamp without time zone,
	 datadevolucao timestamp without time zone
);


ALTER TABLE reservalivro OWNER TO postgres;

--
-- TOC entry 190 (class 1259 OID 16471)
-- Name: reservalivro_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE reservalivro_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE reservalivro_id_seq OWNER TO postgres;

--
-- TOC entry 2259 (class 0 OID 0)
-- Dependencies: 190
-- Name: reservalivro_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE reservalivro_id_seq OWNED BY reservalivro.id;


--
-- TOC entry 203 (class 1259 OID 16563)
-- Name: situacaopedido; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE situacaopedido (
    id integer NOT NULL,
    descricao character varying(50)
);


ALTER TABLE situacaopedido OWNER TO postgres;

--
-- TOC entry 202 (class 1259 OID 16561)
-- Name: situacaopedido_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE situacaopedido_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE situacaopedido_id_seq OWNER TO postgres;

--
-- TOC entry 2260 (class 0 OID 0)
-- Dependencies: 202
-- Name: situacaopedido_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE situacaopedido_id_seq OWNED BY situacaopedido.id;


--
-- TOC entry 201 (class 1259 OID 16555)
-- Name: situacaoreserva; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE situacaoreserva (
    id integer NOT NULL,
    descricao character varying(50)
);


ALTER TABLE situacaoreserva OWNER TO postgres;

--
-- TOC entry 200 (class 1259 OID 16553)
-- Name: situacaoreserva_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE situacaoreserva_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE situacaoreserva_id_seq OWNER TO postgres;

--
-- TOC entry 2261 (class 0 OID 0)
-- Dependencies: 200
-- Name: situacaoreserva_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE situacaoreserva_id_seq OWNED BY situacaoreserva.id;


--
-- TOC entry 205 (class 1259 OID 16581)
-- Name: situacaosugestao; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE situacaosugestao (
    id integer NOT NULL,
    descricao character varying(50)
);


ALTER TABLE situacaosugestao OWNER TO postgres;

--
-- TOC entry 204 (class 1259 OID 16579)
-- Name: situacaosugestao_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE situacaosugestao_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE situacaosugestao_id_seq OWNER TO postgres;

--
-- TOC entry 2262 (class 0 OID 0)
-- Dependencies: 204
-- Name: situacaosugestao_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE situacaosugestao_id_seq OWNED BY situacaosugestao.id;


--
-- TOC entry 193 (class 1259 OID 16491)
-- Name: sugestaolivro; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE sugestaolivro (
    id integer NOT NULL,
    alunoid integer NOT NULL,
    livroid integer NOT NULL,
    datarealizacao timestamp without time zone,
    situacaosugestaoid integer
);


ALTER TABLE sugestaolivro OWNER TO postgres;

--
-- TOC entry 192 (class 1259 OID 16489)
-- Name: sugestaolivro_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE sugestaolivro_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE sugestaolivro_id_seq OWNER TO postgres;

--
-- TOC entry 2263 (class 0 OID 0)
-- Dependencies: 192
-- Name: sugestaolivro_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE sugestaolivro_id_seq OWNED BY sugestaolivro.id;


--
-- TOC entry 2054 (class 2604 OID 16421)
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY aluno ALTER COLUMN id SET DEFAULT nextval('aluno_id_seq'::regclass);


--
-- TOC entry 2056 (class 2604 OID 16437)
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY autor ALTER COLUMN id SET DEFAULT nextval('autor_id_seq'::regclass);


--
-- TOC entry 2062 (class 2604 OID 16542)
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY distribuidora ALTER COLUMN id SET DEFAULT nextval('distribuidora_id_seq'::regclass);


--
-- TOC entry 2055 (class 2604 OID 16429)
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY editora ALTER COLUMN id SET DEFAULT nextval('editora_id_seq'::regclass);


--
-- TOC entry 2057 (class 2604 OID 16445)
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY livro ALTER COLUMN id SET DEFAULT nextval('livro_id_seq'::regclass);


--
-- TOC entry 2060 (class 2604 OID 16515)
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY pedido ALTER COLUMN id SET DEFAULT nextval('pedido_id_seq'::regclass);


--
-- TOC entry 2061 (class 2604 OID 16523)
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY pedidolivro ALTER COLUMN id SET DEFAULT nextval('pedidolivro_id_seq'::regclass);


--
-- TOC entry 2058 (class 2604 OID 16476)
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY reservalivro ALTER COLUMN id SET DEFAULT nextval('reservalivro_id_seq'::regclass);


--
-- TOC entry 2064 (class 2604 OID 16566)
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY situacaopedido ALTER COLUMN id SET DEFAULT nextval('situacaopedido_id_seq'::regclass);


--
-- TOC entry 2063 (class 2604 OID 16558)
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY situacaoreserva ALTER COLUMN id SET DEFAULT nextval('situacaoreserva_id_seq'::regclass);


--
-- TOC entry 2065 (class 2604 OID 16584)
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY situacaosugestao ALTER COLUMN id SET DEFAULT nextval('situacaosugestao_id_seq'::regclass);


--
-- TOC entry 2059 (class 2604 OID 16494)
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY sugestaolivro ALTER COLUMN id SET DEFAULT nextval('sugestaolivro_id_seq'::regclass);


--
-- TOC entry 2220 (class 0 OID 16418)
-- Dependencies: 182
-- Data for Name: aluno; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO aluno VALUES (1, 'Fernando Mesquita');


--
-- TOC entry 2264 (class 0 OID 0)
-- Dependencies: 181
-- Name: aluno_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('aluno_id_seq', 1, true);


--
-- TOC entry 2224 (class 0 OID 16434)
-- Dependencies: 186
-- Data for Name: autor; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO autor VALUES (1, 'Martin Fowler');
INSERT INTO autor VALUES (2, 'Andrew Tanenbaum');
INSERT INTO autor VALUES (3, 'Pressman');
INSERT INTO autor VALUES (4, 'Somerville');
INSERT INTO autor VALUES (5, 'Navathe');


--
-- TOC entry 2265 (class 0 OID 0)
-- Dependencies: 185
-- Name: autor_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('autor_id_seq', 5, true);


--
-- TOC entry 2237 (class 0 OID 16539)
-- Dependencies: 199
-- Data for Name: distribuidora; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO distribuidora VALUES (1, 'Companhia das Letras');
INSERT INTO distribuidora VALUES (2, 'Terra do Sol');
INSERT INTO distribuidora VALUES (3, 'Reino da Leitura');


--
-- TOC entry 2266 (class 0 OID 0)
-- Dependencies: 198
-- Name: distribuidora_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('distribuidora_id_seq', 3, true);


--
-- TOC entry 2222 (class 0 OID 16426)
-- Dependencies: 184
-- Data for Name: editora; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO editora VALUES (1, 'Editora X');
INSERT INTO editora VALUES (2, 'Editora Y');
INSERT INTO editora VALUES (3, 'Editora Z');


--
-- TOC entry 2267 (class 0 OID 0)
-- Dependencies: 183
-- Name: editora_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('editora_id_seq', 3, true);


--
-- TOC entry 2226 (class 0 OID 16442)
-- Dependencies: 188
-- Data for Name: livro; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO livro VALUES (1, 'Livro 1', '123', 'livro teste', 1, '2ª', 10, 4);
INSERT INTO livro VALUES (2, 'Livro 2', '456', 'livro teste 2', 2, 'Especial', 0, 0);
INSERT INTO livro VALUES (3, 'Livro 3', '789', 'livro teste 3', 3, '1ª', 5, 1);
INSERT INTO livro VALUES (4, 'Livro 4', '12', 'livro com acenstos', 2, '2ª', 7, 3);
INSERT INTO livro VALUES (5, 'Livro 5', '345', 'livro teste 5', 1, '3ª', 6, 2);


--
-- TOC entry 2268 (class 0 OID 0)
-- Dependencies: 187
-- Name: livro_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('livro_id_seq', 5, true);


--
-- TOC entry 2227 (class 0 OID 16456)
-- Dependencies: 189
-- Data for Name: livroautor; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO livroautor VALUES (1, 1);
INSERT INTO livroautor VALUES (2, 1);
INSERT INTO livroautor VALUES (2, 2);
INSERT INTO livroautor VALUES (3, 2);
INSERT INTO livroautor VALUES (3, 3);


--
-- TOC entry 2233 (class 0 OID 16512)
-- Dependencies: 195
-- Data for Name: pedido; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO pedido VALUES (34, '12:43:29.448', 1, 'tetet', 3);
INSERT INTO pedido VALUES (35, '12:46:50.967', 3, 'hgjgknl', 3);
INSERT INTO pedido VALUES (36, '13:39:50.337', 1, 'tsfrfr', 3);
INSERT INTO pedido VALUES (37, '13:49:21.314', 3, 'ssd', 3);


--
-- TOC entry 2269 (class 0 OID 0)
-- Dependencies: 194
-- Name: pedido_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('pedido_id_seq', 37, true);


--
-- TOC entry 2235 (class 0 OID 16520)
-- Dependencies: 197
-- Data for Name: pedidolivro; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO pedidolivro VALUES (65, 35, 2, 3, 3);
INSERT INTO pedidolivro VALUES (66, 35, 3, 3, 3);
INSERT INTO pedidolivro VALUES (67, 36, 2, 3, 3);
INSERT INTO pedidolivro VALUES (68, 36, 3, 3, 3);
INSERT INTO pedidolivro VALUES (69, 37, 2, 344, 344);
INSERT INTO pedidolivro VALUES (70, 37, 3, 1, 1);
INSERT INTO pedidolivro VALUES (63, 34, 2, 2, 2);
INSERT INTO pedidolivro VALUES (64, 34, 3, 3, 3);


--
-- TOC entry 2270 (class 0 OID 0)
-- Dependencies: 196
-- Name: pedidolivro_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('pedidolivro_id_seq', 70, true);


--
-- TOC entry 2229 (class 0 OID 16473)
-- Dependencies: 191
-- Data for Name: reservalivro; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 2271 (class 0 OID 0)
-- Dependencies: 190
-- Name: reservalivro_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('reservalivro_id_seq', 51, true);


--
-- TOC entry 2241 (class 0 OID 16563)
-- Dependencies: 203
-- Data for Name: situacaopedido; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO situacaopedido VALUES (1, 'Aguardando Encaminhamento');
INSERT INTO situacaopedido VALUES (3, 'Processado pela Distribuidora');
INSERT INTO situacaopedido VALUES (2, 'Encaminhado à Distribuidora');


--
-- TOC entry 2272 (class 0 OID 0)
-- Dependencies: 202
-- Name: situacaopedido_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('situacaopedido_id_seq', 3, true);


--
-- TOC entry 2239 (class 0 OID 16555)
-- Dependencies: 201
-- Data for Name: situacaoreserva; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO situacaoreserva VALUES (1, 'Aguardando Empréstimo');
INSERT INTO situacaoreserva VALUES (2, 'Emprestado');
INSERT INTO situacaoreserva VALUES (3, 'Cancelada');


--
-- TOC entry 2273 (class 0 OID 0)
-- Dependencies: 200
-- Name: situacaoreserva_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('situacaoreserva_id_seq', 3, true);


--
-- TOC entry 2243 (class 0 OID 16581)
-- Dependencies: 205
-- Data for Name: situacaosugestao; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO situacaosugestao VALUES (1, 'Em análise');
INSERT INTO situacaosugestao VALUES (3, 'Cancelada');
INSERT INTO situacaosugestao VALUES (2, 'Adquirida');
INSERT INTO situacaosugestao VALUES (4, 'Não Disponivel');


--
-- TOC entry 2274 (class 0 OID 0)
-- Dependencies: 204
-- Name: situacaosugestao_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('situacaosugestao_id_seq', 4, true);


--
-- TOC entry 2231 (class 0 OID 16491)
-- Dependencies: 193
-- Data for Name: sugestaolivro; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO sugestaolivro VALUES (6, 1, 2, '2016-12-07 21:52:24.138', 3);
INSERT INTO sugestaolivro VALUES (8, 1, 3, '2016-12-07 23:41:19.588', 3);


--
-- TOC entry 2275 (class 0 OID 0)
-- Dependencies: 192
-- Name: sugestaolivro_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('sugestaolivro_id_seq', 8, true);


--
-- TOC entry 2067 (class 2606 OID 16423)
-- Name: aluno_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY aluno
    ADD CONSTRAINT aluno_pk PRIMARY KEY (id);


--
-- TOC entry 2071 (class 2606 OID 16439)
-- Name: autor_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY autor
    ADD CONSTRAINT autor_pk PRIMARY KEY (id);


--
-- TOC entry 2085 (class 2606 OID 16544)
-- Name: distribuidora_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY distribuidora
    ADD CONSTRAINT distribuidora_pk PRIMARY KEY (id);


--
-- TOC entry 2069 (class 2606 OID 16431)
-- Name: editora_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY editora
    ADD CONSTRAINT editora_pk PRIMARY KEY (id);


--
-- TOC entry 2073 (class 2606 OID 16450)
-- Name: livro_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY livro
    ADD CONSTRAINT livro_pk PRIMARY KEY (id);


--
-- TOC entry 2075 (class 2606 OID 16460)
-- Name: livroautor_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY livroautor
    ADD CONSTRAINT livroautor_pk PRIMARY KEY (autorid, livroid);


--
-- TOC entry 2081 (class 2606 OID 16517)
-- Name: pedido_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY pedido
    ADD CONSTRAINT pedido_pk PRIMARY KEY (id);


--
-- TOC entry 2083 (class 2606 OID 16525)
-- Name: pedidolivro_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY pedidolivro
    ADD CONSTRAINT pedidolivro_pk PRIMARY KEY (id);


--
-- TOC entry 2077 (class 2606 OID 16478)
-- Name: reserva_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY reservalivro
    ADD CONSTRAINT reserva_pk PRIMARY KEY (id);


--
-- TOC entry 2089 (class 2606 OID 16568)
-- Name: situacaopedido_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY situacaopedido
    ADD CONSTRAINT situacaopedido_pk PRIMARY KEY (id);


--
-- TOC entry 2087 (class 2606 OID 16560)
-- Name: situacaoreserva_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY situacaoreserva
    ADD CONSTRAINT situacaoreserva_pk PRIMARY KEY (id);


--
-- TOC entry 2091 (class 2606 OID 16586)
-- Name: situacaosugestao_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY situacaosugestao
    ADD CONSTRAINT situacaosugestao_pk PRIMARY KEY (id);


--
-- TOC entry 2079 (class 2606 OID 16499)
-- Name: sugestao_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY sugestaolivro
    ADD CONSTRAINT sugestao_pk PRIMARY KEY (id);


--
-- TOC entry 2095 (class 2606 OID 16479)
-- Name: aluno_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY reservalivro
    ADD CONSTRAINT aluno_fk FOREIGN KEY (alunoid) REFERENCES aluno(id);


--
-- TOC entry 2098 (class 2606 OID 16500)
-- Name: aluno_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY sugestaolivro
    ADD CONSTRAINT aluno_fk FOREIGN KEY (alunoid) REFERENCES aluno(id);


--
-- TOC entry 2094 (class 2606 OID 16466)
-- Name: autor_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY livroautor
    ADD CONSTRAINT autor_fk FOREIGN KEY (autorid) REFERENCES autor(id);


--
-- TOC entry 2101 (class 2606 OID 16545)
-- Name: distribuidora_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY pedido
    ADD CONSTRAINT distribuidora_fk FOREIGN KEY (distribuidoraid) REFERENCES distribuidora(id);


--
-- TOC entry 2092 (class 2606 OID 16451)
-- Name: editora_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY livro
    ADD CONSTRAINT editora_fk FOREIGN KEY (editoraid) REFERENCES editora(id);


--
-- TOC entry 2093 (class 2606 OID 16461)
-- Name: livro_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY livroautor
    ADD CONSTRAINT livro_fk FOREIGN KEY (livroid) REFERENCES livro(id);


--
-- TOC entry 2096 (class 2606 OID 16484)
-- Name: livro_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY reservalivro
    ADD CONSTRAINT livro_fk FOREIGN KEY (livroid) REFERENCES livro(id);


--
-- TOC entry 2099 (class 2606 OID 16505)
-- Name: livro_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY sugestaolivro
    ADD CONSTRAINT livro_fk FOREIGN KEY (livroid) REFERENCES livro(id);


--
-- TOC entry 2104 (class 2606 OID 16531)
-- Name: livro_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY pedidolivro
    ADD CONSTRAINT livro_fk FOREIGN KEY (livroid) REFERENCES livro(id);


--
-- TOC entry 2103 (class 2606 OID 16526)
-- Name: pedido_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY pedidolivro
    ADD CONSTRAINT pedido_fk FOREIGN KEY (pedidoid) REFERENCES pedido(id);


--
-- TOC entry 2102 (class 2606 OID 16592)
-- Name: situacaopedidoid; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY pedido
    ADD CONSTRAINT situacaopedidoid FOREIGN KEY (situacaopedidoid) REFERENCES situacaopedido(id);


--
-- TOC entry 2097 (class 2606 OID 16569)
-- Name: situacaoreserva_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY reservalivro
    ADD CONSTRAINT situacaoreserva_fk FOREIGN KEY (situacaoreservaid) REFERENCES situacaoreserva(id);


--
-- TOC entry 2100 (class 2606 OID 16587)
-- Name: situacaosugestao_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY sugestaolivro
    ADD CONSTRAINT situacaosugestao_fk FOREIGN KEY (situacaosugestaoid) REFERENCES situacaosugestao(id);


--
-- TOC entry 2250 (class 0 OID 0)
-- Dependencies: 6
-- Name: public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;


-- Completed on 2016-12-11 20:57:05

--
-- PostgreSQL database dump complete
--

