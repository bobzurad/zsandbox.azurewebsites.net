
--this is invalid --v  d is not in scope
--area d = pi * (r * r)
--r = d / 2


--this is ok
area d = pi * (r * r)
  where r = d / 2
