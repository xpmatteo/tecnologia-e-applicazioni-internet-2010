
create table users (
    id integer identity,
    login varchar(255) not null,
    encrypted_password varchar(255) not null,
    primary key(id),
    unique(login)
);

 -- password is "secret", obtained with
 -- echo -n "secret" | sha1sum
insert into users (login, encrypted_password) 
    values ('admin', 'e5e9fa1ba31ecd1ae84f75caaa474f3a663f05f4');
    
commit;
