

INSERT INTO public."Categoria"(
            "categoriaId", descricao)
    VALUES (1, 'banana');
INSERT INTO public."Categoria"(
            "categoriaId", descricao)
    VALUES (2, 'maca');
    INSERT INTO public."Categoria"(
            "categoriaId", descricao)
    VALUES (3, 'uva');

    
    INSERT INTO public."Produto"(
            "produtoId", descricao, valor, peso, "categoriaId")
    VALUES (1, 'caturra', 1, 1, 1);
    INSERT INTO public."Produto"(
            "produtoId", descricao, valor, peso, "categoriaId")
    VALUES (2, 'maca', 2, 1, 1);
    INSERT INTO public."Produto"(
            "produtoId", descricao, valor, peso, "categoriaId")
    VALUES (3, 'ouro', 3, 1, 1);


    INSERT INTO public."Produto"(
            "produtoId", descricao, valor, peso, "categoriaId")
    VALUES (4, 'argentina', 2, 1, 2);
    
    INSERT INTO public."Produto"(
            "produtoId", descricao, valor, peso, "categoriaId")
    VALUES (5, 'brasileira', 4, 1, 2);

    
    INSERT INTO public."Produto"(
            "produtoId", descricao, valor, peso, "categoriaId")
    VALUES (6, 'grande', 5, 1, 3);
    
    INSERT INTO public."Produto"(
            "produtoId", descricao, valor, peso, "categoriaId")
    VALUES (7, 'media', 6, 1, 3);
    
    INSERT INTO public."Produto"(
            "produtoId", descricao, valor, peso, "categoriaId")
    VALUES (8, 'pequena', 8, 1, 3);
INSERT INTO public."Cliente"(
            "clienteId")
    VALUES (1);

    INSERT INTO public.avalia(
            satisfacao, "produtoId", "clienteId")
    VALUES (2, 1, 1);
INSERT INTO public.avalia(
            satisfacao, "produtoId", "clienteId")
    VALUES (3, 2, 1);
INSERT INTO public.avalia(
            satisfacao, "produtoId", "clienteId")
    VALUES (2, 3, 1);
INSERT INTO public.avalia(
            satisfacao, "produtoId", "clienteId")
    VALUES (1, 4, 1);
INSERT INTO public.avalia(
            satisfacao, "produtoId", "clienteId")
    VALUES (4, 5, 1);
    INSERT INTO public.avalia(
            satisfacao, "produtoId", "clienteId")
    VALUES (5, 6, 1);
    INSERT INTO public.avalia(
            satisfacao, "produtoId", "clienteId")
    VALUES (4, 7, 1);
    INSERT INTO public.avalia(
            satisfacao, "produtoId", "clienteId")
    VALUES (6, 8, 1);




    

    
    
    
    
    
    