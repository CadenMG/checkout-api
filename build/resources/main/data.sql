INSERT INTO public.person(id, first_name, last_name) VALUES
  (1, 'Bob', 'Man'),
  (2, 'Alice', 'Woman');

INSERT INTO public.item(id, price, name) VALUES
  (1, 5.25, 'Pen'),
  (2, 100.15, 'Chair');

INSERT INTO public.cart_item(person_id, item_id, quantity) VALUES
  (1, 1, 1),
  (2, 2, 2);

-- Bob Man has 1 Pen, and Alice Woman has 2 Chairs