insert into member(email, name, nick_name, password, profile_image_url)
values('abc@naver.com','nick','isKing','$2a$10$IHDDc06yJr4v9JpXysrYceM0wc/Tgngi4qtWHDhgKWibMDi5uRhMe','https://media.gettyimages.com/photos/tree-in-park-picture-id164850397?s=612x612');

insert into member(email, name, nick_name, password, profile_image_url)
values('abc@gmail.com','king','isNick','$2a$10$IHDDc06yJr4v9JpXysrYceM0wc/Tgngi4qtWHDhgKWibMDi5uRhMe','https://images.pexels.com/photos/67636/rose-blue-flower-rose-blooms-67636.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500');


insert into article(contents, image_url, author) values('닉은 왕이다.', 'https://zzazanstagram-zzazan.s3.ap-northeast-2.amazonaws.com/upload/2019-08-21T09%3A27%3A32.100Apple-ios-13-sign-in-screen-iphone-xs-06032019.jpg', '1');
insert into article(contents, image_url, author) values('왕은 닉이다.', 'https://zzazanstagram-zzazan.s3.ap-northeast-2.amazonaws.com/upload/2019-08-21T09%3A27%3A32.100Apple-ios-13-sign-in-screen-iphone-xs-06032019.jpg', '1');

insert into comment(contents, article_id, commenter_id) values('닉은 왕이다', '1', '1');
insert into comment(contents, article_id, commenter_id) values('닉은 왕따이다', '1', '1');
insert into comment(contents, article_id, commenter_id) values('닉은 왕꿈틀이다', '1', '1');
