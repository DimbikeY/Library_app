PGDMP                         {         
   Library_DB    15.4    15.4                0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false                       0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false                       0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false                       1262    16607 
   Library_DB    DATABASE     �   CREATE DATABASE "Library_DB" WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'Russian_Russia.1251';
    DROP DATABASE "Library_DB";
                postgres    false            �            1259    16762    book    TABLE     �   CREATE TABLE public.book (
    id integer NOT NULL,
    name character varying(50),
    author character varying(50),
    year integer,
    person_id integer
);
    DROP TABLE public.book;
       public         heap    postgres    false            �            1259    16761    book_id_seq    SEQUENCE     �   ALTER TABLE public.book ALTER COLUMN id ADD GENERATED BY DEFAULT AS IDENTITY (
    SEQUENCE NAME public.book_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            public          postgres    false    217            �            1259    16754    person    TABLE     j   CREATE TABLE public.person (
    id integer NOT NULL,
    name character varying(50),
    year integer
);
    DROP TABLE public.person;
       public         heap    postgres    false            �            1259    16753    person_id_seq    SEQUENCE     �   ALTER TABLE public.person ALTER COLUMN id ADD GENERATED BY DEFAULT AS IDENTITY (
    SEQUENCE NAME public.person_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            public          postgres    false    215                      0    16762    book 
   TABLE DATA           A   COPY public.book (id, name, author, year, person_id) FROM stdin;
    public          postgres    false    217   F                  0    16754    person 
   TABLE DATA           0   COPY public.person (id, name, year) FROM stdin;
    public          postgres    false    215   �       	           0    0    book_id_seq    SEQUENCE SET     :   SELECT pg_catalog.setval('public.book_id_seq', 10, true);
          public          postgres    false    216            
           0    0    person_id_seq    SEQUENCE SET     <   SELECT pg_catalog.setval('public.person_id_seq', 10, true);
          public          postgres    false    214            o           2606    16766    book book_pkey 
   CONSTRAINT     L   ALTER TABLE ONLY public.book
    ADD CONSTRAINT book_pkey PRIMARY KEY (id);
 8   ALTER TABLE ONLY public.book DROP CONSTRAINT book_pkey;
       public            postgres    false    217            k           2606    16760    person person_name_key 
   CONSTRAINT     Q   ALTER TABLE ONLY public.person
    ADD CONSTRAINT person_name_key UNIQUE (name);
 @   ALTER TABLE ONLY public.person DROP CONSTRAINT person_name_key;
       public            postgres    false    215            m           2606    16758    person person_pkey 
   CONSTRAINT     P   ALTER TABLE ONLY public.person
    ADD CONSTRAINT person_pkey PRIMARY KEY (id);
 <   ALTER TABLE ONLY public.person DROP CONSTRAINT person_pkey;
       public            postgres    false    215            p           2606    16767    book book_person_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.book
    ADD CONSTRAINT book_person_id_fkey FOREIGN KEY (person_id) REFERENCES public.person(id) ON DELETE RESTRICT;
 B   ALTER TABLE ONLY public.book DROP CONSTRAINT book_person_id_fkey;
       public          postgres    false    215    3181    217               -  x�e��n�0���S쭧FH�U���z��$[p�z��)J���p�|�Ϸ3�I��f������V1�V�����mURL3uz���H�5ւ�5�q��p�6����Dt>	�Lvy��H|Fx�Z+WiSU�i�k�b����A���ӵ��T�'�@fc�g훋^��A�x)A���"�%��xI�}�Yz����H�3A��Ҁ/Br����|�f�2�CO.�mc��-��p�ù�Q=<.Mө�v�C�#�H�=O1��P]�?�l�I&�]��+��C���� B�SE�E~�          �   x�%��
�0�ϻO�O i�6=V*HQ+��%�@�#i���6z��/�6O���@V�sh�����J�N�0J[J��2�7�wl?��4�B,��:N���0����4���Kh����K����y��A��I��l-+G�W�:���L@=+:���o+D�w^62     