PGDMP      
        
        }         
   inventario    17.4    17.4 C    =           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                           false            >           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                           false            ?           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                           false            @           1262    16388 
   inventario    DATABASE     p   CREATE DATABASE inventario WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'es-ES';
    DROP DATABASE inventario;
                     postgres    false            A           0    0    DATABASE inventario    ACL     ,   GRANT ALL ON DATABASE inventario TO miguel;
                        postgres    false    4928                        2615    16626    public    SCHEMA     2   -- *not* creating schema, since initdb creates it
 2   -- *not* dropping schema, since initdb creates it
                     postgres    false            B           0    0    SCHEMA public    COMMENT         COMMENT ON SCHEMA public IS '';
                        postgres    false    5            C           0    0    SCHEMA public    ACL     Q   REVOKE USAGE ON SCHEMA public FROM PUBLIC;
GRANT ALL ON SCHEMA public TO PUBLIC;
                        postgres    false    5            �            1259    16703    distribucion    TABLE     �   CREATE TABLE public.distribucion (
    id bigint NOT NULL,
    salida_materia_prima_id bigint NOT NULL,
    destino character varying(255) NOT NULL,
    fecha date NOT NULL,
    cantidad integer NOT NULL
);
     DROP TABLE public.distribucion;
       public         heap r       miguel    false    5            �            1259    16702    distribucion_id_seq    SEQUENCE     |   CREATE SEQUENCE public.distribucion_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 *   DROP SEQUENCE public.distribucion_id_seq;
       public               miguel    false    229    5            D           0    0    distribucion_id_seq    SEQUENCE OWNED BY     K   ALTER SEQUENCE public.distribucion_id_seq OWNED BY public.distribucion.id;
          public               miguel    false    228            �            1259    16674    entradamateriaprima    TABLE     �   CREATE TABLE public.entradamateriaprima (
    id bigint NOT NULL,
    materia_prima_id bigint NOT NULL,
    cantidad integer NOT NULL,
    fecha date NOT NULL,
    orden_compra_id bigint
);
 '   DROP TABLE public.entradamateriaprima;
       public         heap r       miguel    false    5            �            1259    16673    entradamateriaprima_id_seq    SEQUENCE     �   CREATE SEQUENCE public.entradamateriaprima_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 1   DROP SEQUENCE public.entradamateriaprima_id_seq;
       public               miguel    false    5    225            E           0    0    entradamateriaprima_id_seq    SEQUENCE OWNED BY     Y   ALTER SEQUENCE public.entradamateriaprima_id_seq OWNED BY public.entradamateriaprima.id;
          public               miguel    false    224            �            1259    16627    flyway_schema_history    TABLE     �  CREATE TABLE public.flyway_schema_history (
    installed_rank integer NOT NULL,
    version character varying(50),
    description character varying(200) NOT NULL,
    type character varying(20) NOT NULL,
    script character varying(1000) NOT NULL,
    checksum integer,
    installed_by character varying(100) NOT NULL,
    installed_on timestamp without time zone DEFAULT now() NOT NULL,
    execution_time integer NOT NULL,
    success boolean NOT NULL
);
 )   DROP TABLE public.flyway_schema_history;
       public         heap r       miguel    false    5            �            1259    16646    materiaprima    TABLE     �   CREATE TABLE public.materiaprima (
    id bigint NOT NULL,
    nombre character varying(255) NOT NULL,
    descripcion text,
    unidad_medida character varying(50),
    stock_actual integer DEFAULT 0,
    proveedor_id bigint
);
     DROP TABLE public.materiaprima;
       public         heap r       miguel    false    5            �            1259    16645    materiaprima_id_seq    SEQUENCE     |   CREATE SEQUENCE public.materiaprima_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 *   DROP SEQUENCE public.materiaprima_id_seq;
       public               miguel    false    5    221            F           0    0    materiaprima_id_seq    SEQUENCE OWNED BY     K   ALTER SEQUENCE public.materiaprima_id_seq OWNED BY public.materiaprima.id;
          public               miguel    false    220            �            1259    16661    ordencompra    TABLE     �   CREATE TABLE public.ordencompra (
    id bigint NOT NULL,
    proveedor_id bigint NOT NULL,
    fecha date NOT NULL,
    estado character varying(50) DEFAULT 'Pendiente'::character varying,
    total numeric(10,2) NOT NULL
);
    DROP TABLE public.ordencompra;
       public         heap r       miguel    false    5            �            1259    16660    ordencompra_id_seq    SEQUENCE     {   CREATE SEQUENCE public.ordencompra_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 )   DROP SEQUENCE public.ordencompra_id_seq;
       public               miguel    false    223    5            G           0    0    ordencompra_id_seq    SEQUENCE OWNED BY     I   ALTER SEQUENCE public.ordencompra_id_seq OWNED BY public.ordencompra.id;
          public               miguel    false    222            �            1259    16637 	   proveedor    TABLE     �   CREATE TABLE public.proveedor (
    id bigint NOT NULL,
    nombre character varying(255) NOT NULL,
    contacto character varying(255),
    telefono character varying(50),
    email character varying(255),
    direccion text
);
    DROP TABLE public.proveedor;
       public         heap r       miguel    false    5            �            1259    16636    proveedor_id_seq    SEQUENCE     y   CREATE SEQUENCE public.proveedor_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 '   DROP SEQUENCE public.proveedor_id_seq;
       public               miguel    false    219    5            H           0    0    proveedor_id_seq    SEQUENCE OWNED BY     E   ALTER SEQUENCE public.proveedor_id_seq OWNED BY public.proveedor.id;
          public               miguel    false    218            �            1259    16715    reportes    TABLE     �   CREATE TABLE public.reportes (
    id bigint NOT NULL,
    tipo character varying(255) NOT NULL,
    descripcion text,
    fecha_generacion timestamp without time zone DEFAULT CURRENT_TIMESTAMP
);
    DROP TABLE public.reportes;
       public         heap r       miguel    false    5            �            1259    16714    reportes_id_seq    SEQUENCE     x   CREATE SEQUENCE public.reportes_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 &   DROP SEQUENCE public.reportes_id_seq;
       public               miguel    false    5    231            I           0    0    reportes_id_seq    SEQUENCE OWNED BY     C   ALTER SEQUENCE public.reportes_id_seq OWNED BY public.reportes.id;
          public               miguel    false    230            �            1259    16691    salidamateriaprima    TABLE     �   CREATE TABLE public.salidamateriaprima (
    id bigint NOT NULL,
    materia_prima_id bigint NOT NULL,
    cantidad integer NOT NULL,
    fecha date NOT NULL,
    destino character varying(255)
);
 &   DROP TABLE public.salidamateriaprima;
       public         heap r       miguel    false    5            �            1259    16690    salidamateriaprima_id_seq    SEQUENCE     �   CREATE SEQUENCE public.salidamateriaprima_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 0   DROP SEQUENCE public.salidamateriaprima_id_seq;
       public               miguel    false    5    227            J           0    0    salidamateriaprima_id_seq    SEQUENCE OWNED BY     W   ALTER SEQUENCE public.salidamateriaprima_id_seq OWNED BY public.salidamateriaprima.id;
          public               miguel    false    226            �           2604    16706    distribucion id    DEFAULT     r   ALTER TABLE ONLY public.distribucion ALTER COLUMN id SET DEFAULT nextval('public.distribucion_id_seq'::regclass);
 >   ALTER TABLE public.distribucion ALTER COLUMN id DROP DEFAULT;
       public               miguel    false    229    228    229                       2604    16677    entradamateriaprima id    DEFAULT     �   ALTER TABLE ONLY public.entradamateriaprima ALTER COLUMN id SET DEFAULT nextval('public.entradamateriaprima_id_seq'::regclass);
 E   ALTER TABLE public.entradamateriaprima ALTER COLUMN id DROP DEFAULT;
       public               miguel    false    225    224    225            {           2604    16649    materiaprima id    DEFAULT     r   ALTER TABLE ONLY public.materiaprima ALTER COLUMN id SET DEFAULT nextval('public.materiaprima_id_seq'::regclass);
 >   ALTER TABLE public.materiaprima ALTER COLUMN id DROP DEFAULT;
       public               miguel    false    220    221    221            }           2604    16664    ordencompra id    DEFAULT     p   ALTER TABLE ONLY public.ordencompra ALTER COLUMN id SET DEFAULT nextval('public.ordencompra_id_seq'::regclass);
 =   ALTER TABLE public.ordencompra ALTER COLUMN id DROP DEFAULT;
       public               miguel    false    222    223    223            z           2604    16640    proveedor id    DEFAULT     l   ALTER TABLE ONLY public.proveedor ALTER COLUMN id SET DEFAULT nextval('public.proveedor_id_seq'::regclass);
 ;   ALTER TABLE public.proveedor ALTER COLUMN id DROP DEFAULT;
       public               miguel    false    218    219    219            �           2604    16718    reportes id    DEFAULT     j   ALTER TABLE ONLY public.reportes ALTER COLUMN id SET DEFAULT nextval('public.reportes_id_seq'::regclass);
 :   ALTER TABLE public.reportes ALTER COLUMN id DROP DEFAULT;
       public               miguel    false    230    231    231            �           2604    16694    salidamateriaprima id    DEFAULT     ~   ALTER TABLE ONLY public.salidamateriaprima ALTER COLUMN id SET DEFAULT nextval('public.salidamateriaprima_id_seq'::regclass);
 D   ALTER TABLE public.salidamateriaprima ALTER COLUMN id DROP DEFAULT;
       public               miguel    false    226    227    227            8          0    16703    distribucion 
   TABLE DATA           ]   COPY public.distribucion (id, salida_materia_prima_id, destino, fecha, cantidad) FROM stdin;
    public               miguel    false    229   �Q       4          0    16674    entradamateriaprima 
   TABLE DATA           e   COPY public.entradamateriaprima (id, materia_prima_id, cantidad, fecha, orden_compra_id) FROM stdin;
    public               miguel    false    225   �Q       ,          0    16627    flyway_schema_history 
   TABLE DATA           �   COPY public.flyway_schema_history (installed_rank, version, description, type, script, checksum, installed_by, installed_on, execution_time, success) FROM stdin;
    public               miguel    false    217   �Q       0          0    16646    materiaprima 
   TABLE DATA           j   COPY public.materiaprima (id, nombre, descripcion, unidad_medida, stock_actual, proveedor_id) FROM stdin;
    public               miguel    false    221   9R       2          0    16661    ordencompra 
   TABLE DATA           M   COPY public.ordencompra (id, proveedor_id, fecha, estado, total) FROM stdin;
    public               miguel    false    223   VR       .          0    16637 	   proveedor 
   TABLE DATA           U   COPY public.proveedor (id, nombre, contacto, telefono, email, direccion) FROM stdin;
    public               miguel    false    219   sR       :          0    16715    reportes 
   TABLE DATA           K   COPY public.reportes (id, tipo, descripcion, fecha_generacion) FROM stdin;
    public               miguel    false    231   �R       6          0    16691    salidamateriaprima 
   TABLE DATA           \   COPY public.salidamateriaprima (id, materia_prima_id, cantidad, fecha, destino) FROM stdin;
    public               miguel    false    227   �R       K           0    0    distribucion_id_seq    SEQUENCE SET     B   SELECT pg_catalog.setval('public.distribucion_id_seq', 1, false);
          public               miguel    false    228            L           0    0    entradamateriaprima_id_seq    SEQUENCE SET     I   SELECT pg_catalog.setval('public.entradamateriaprima_id_seq', 1, false);
          public               miguel    false    224            M           0    0    materiaprima_id_seq    SEQUENCE SET     B   SELECT pg_catalog.setval('public.materiaprima_id_seq', 1, false);
          public               miguel    false    220            N           0    0    ordencompra_id_seq    SEQUENCE SET     A   SELECT pg_catalog.setval('public.ordencompra_id_seq', 1, false);
          public               miguel    false    222            O           0    0    proveedor_id_seq    SEQUENCE SET     ?   SELECT pg_catalog.setval('public.proveedor_id_seq', 1, false);
          public               miguel    false    218            P           0    0    reportes_id_seq    SEQUENCE SET     >   SELECT pg_catalog.setval('public.reportes_id_seq', 1, false);
          public               miguel    false    230            Q           0    0    salidamateriaprima_id_seq    SEQUENCE SET     H   SELECT pg_catalog.setval('public.salidamateriaprima_id_seq', 1, false);
          public               miguel    false    226            �           2606    16708    distribucion distribucion_pkey 
   CONSTRAINT     \   ALTER TABLE ONLY public.distribucion
    ADD CONSTRAINT distribucion_pkey PRIMARY KEY (id);
 H   ALTER TABLE ONLY public.distribucion DROP CONSTRAINT distribucion_pkey;
       public                 miguel    false    229            �           2606    16679 ,   entradamateriaprima entradamateriaprima_pkey 
   CONSTRAINT     j   ALTER TABLE ONLY public.entradamateriaprima
    ADD CONSTRAINT entradamateriaprima_pkey PRIMARY KEY (id);
 V   ALTER TABLE ONLY public.entradamateriaprima DROP CONSTRAINT entradamateriaprima_pkey;
       public                 miguel    false    225            �           2606    16634 .   flyway_schema_history flyway_schema_history_pk 
   CONSTRAINT     x   ALTER TABLE ONLY public.flyway_schema_history
    ADD CONSTRAINT flyway_schema_history_pk PRIMARY KEY (installed_rank);
 X   ALTER TABLE ONLY public.flyway_schema_history DROP CONSTRAINT flyway_schema_history_pk;
       public                 miguel    false    217            �           2606    16654    materiaprima materiaprima_pkey 
   CONSTRAINT     \   ALTER TABLE ONLY public.materiaprima
    ADD CONSTRAINT materiaprima_pkey PRIMARY KEY (id);
 H   ALTER TABLE ONLY public.materiaprima DROP CONSTRAINT materiaprima_pkey;
       public                 miguel    false    221            �           2606    16667    ordencompra ordencompra_pkey 
   CONSTRAINT     Z   ALTER TABLE ONLY public.ordencompra
    ADD CONSTRAINT ordencompra_pkey PRIMARY KEY (id);
 F   ALTER TABLE ONLY public.ordencompra DROP CONSTRAINT ordencompra_pkey;
       public                 miguel    false    223            �           2606    16644    proveedor proveedor_pkey 
   CONSTRAINT     V   ALTER TABLE ONLY public.proveedor
    ADD CONSTRAINT proveedor_pkey PRIMARY KEY (id);
 B   ALTER TABLE ONLY public.proveedor DROP CONSTRAINT proveedor_pkey;
       public                 miguel    false    219            �           2606    16723    reportes reportes_pkey 
   CONSTRAINT     T   ALTER TABLE ONLY public.reportes
    ADD CONSTRAINT reportes_pkey PRIMARY KEY (id);
 @   ALTER TABLE ONLY public.reportes DROP CONSTRAINT reportes_pkey;
       public                 miguel    false    231            �           2606    16696 *   salidamateriaprima salidamateriaprima_pkey 
   CONSTRAINT     h   ALTER TABLE ONLY public.salidamateriaprima
    ADD CONSTRAINT salidamateriaprima_pkey PRIMARY KEY (id);
 T   ALTER TABLE ONLY public.salidamateriaprima DROP CONSTRAINT salidamateriaprima_pkey;
       public                 miguel    false    227            �           1259    16635    flyway_schema_history_s_idx    INDEX     `   CREATE INDEX flyway_schema_history_s_idx ON public.flyway_schema_history USING btree (success);
 /   DROP INDEX public.flyway_schema_history_s_idx;
       public                 miguel    false    217            �           2606    16709 6   distribucion distribucion_salida_materia_prima_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.distribucion
    ADD CONSTRAINT distribucion_salida_materia_prima_id_fkey FOREIGN KEY (salida_materia_prima_id) REFERENCES public.salidamateriaprima(id) ON DELETE CASCADE;
 `   ALTER TABLE ONLY public.distribucion DROP CONSTRAINT distribucion_salida_materia_prima_id_fkey;
       public               miguel    false    4752    229    227            �           2606    16680 =   entradamateriaprima entradamateriaprima_materia_prima_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.entradamateriaprima
    ADD CONSTRAINT entradamateriaprima_materia_prima_id_fkey FOREIGN KEY (materia_prima_id) REFERENCES public.materiaprima(id) ON DELETE CASCADE;
 g   ALTER TABLE ONLY public.entradamateriaprima DROP CONSTRAINT entradamateriaprima_materia_prima_id_fkey;
       public               miguel    false    221    4746    225            �           2606    16685 <   entradamateriaprima entradamateriaprima_orden_compra_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.entradamateriaprima
    ADD CONSTRAINT entradamateriaprima_orden_compra_id_fkey FOREIGN KEY (orden_compra_id) REFERENCES public.ordencompra(id) ON DELETE SET NULL;
 f   ALTER TABLE ONLY public.entradamateriaprima DROP CONSTRAINT entradamateriaprima_orden_compra_id_fkey;
       public               miguel    false    225    4748    223            �           2606    16655 +   materiaprima materiaprima_proveedor_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.materiaprima
    ADD CONSTRAINT materiaprima_proveedor_id_fkey FOREIGN KEY (proveedor_id) REFERENCES public.proveedor(id) ON DELETE SET NULL;
 U   ALTER TABLE ONLY public.materiaprima DROP CONSTRAINT materiaprima_proveedor_id_fkey;
       public               miguel    false    221    219    4744            �           2606    16668 )   ordencompra ordencompra_proveedor_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.ordencompra
    ADD CONSTRAINT ordencompra_proveedor_id_fkey FOREIGN KEY (proveedor_id) REFERENCES public.proveedor(id) ON DELETE CASCADE;
 S   ALTER TABLE ONLY public.ordencompra DROP CONSTRAINT ordencompra_proveedor_id_fkey;
       public               miguel    false    219    4744    223            �           2606    16697 ;   salidamateriaprima salidamateriaprima_materia_prima_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.salidamateriaprima
    ADD CONSTRAINT salidamateriaprima_materia_prima_id_fkey FOREIGN KEY (materia_prima_id) REFERENCES public.materiaprima(id) ON DELETE CASCADE;
 e   ALTER TABLE ONLY public.salidamateriaprima DROP CONSTRAINT salidamateriaprima_materia_prima_id_fkey;
       public               miguel    false    221    4746    227            8      x������ � �      4      x������ � �      ,   `   x�-�A
� ���^ �3�3���U ��ݿ�x��`�#�4g]�%�����1��6יQ�����v�B�*��&iٵ.	�q"(j2J��&�      0      x������ � �      2      x������ � �      .      x������ � �      :      x������ � �      6      x������ � �     