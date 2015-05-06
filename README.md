# MyDissertation

Getting users

http://uzhotels.herokuapp.com/users/list

Add user

http://uzhotels.herokuapp.com/users/add

{<br/>
  "username": "JohnDoe",<br/>
  "email": "johndoe@gmail.com",<br/>
  "age": 33<br/>
}<br/>

Get hotels

http://uzhotels.herokuapp.com/hotels/list


http://uzhotels.herokuapp.com/hotels/add

http://localhost:9000/hotels/add

Content-Type: application/json

{
	"name": "MyHotel",
	"phone": "+1234567890",
	"fax":"9876543210",
	"email": "myhotel@gmail.com",
	"webSite": "myhotel.uz",
	"rate": "4",
	"image": "aaa.uz/hotel.png",
	"star": 5,
	"distCenter": "0.2 km",
  	"hotelType":1,
  	"entrance":"15:00",
  	"exit":"12:00",
	"regionId": 1,
	"cityId": 1,
	"address": "my street way",
	"latitude": 123.001,
	"longitude": 321.002,
	"priceId": 1,
    "premium": false
}

{
	"name": "Fayz",
	"phone": "+1234567888",
	"fax":"9876543333",
	"email": "fayz@gmail.com",
	"webSite": "fayz.uz",
	"rate": "4",
	"image": "fayz.uz/hotel.png",
	"star": 4,
	"distCenter": "0.15 km",
  	"hotelType":1,
  	"entrance":"15:00",
  	"exit":"12:00",
	"regionId": 1,
	"cityId": 1,
	"address": "Al-Khorezm, 39",
	"latitude": 110.985,
	"longitude": 454.475,
	"priceId": 1,
    "premium": true
}


{
	"name": "Khorezm Palace",
	"phone": "+1234567777",
	"fax":"9876543000",
	"email": "kh-palace@gmail.com",
	"webSite": "kh-palace.uz",
	"rate": "5",
	"image": "kh-palace.uz/hotel.png",
	"star": 5,
	"distCenter": "0.1 km",
  	"hotelType":1,
  	"entrance":"15:00",
  	"exit":"12:00",
	"regionId": 1,
	"cityId": 1,
	"address": "Novo Urgench, 47",
	"latitude": 123.001,
	"longitude": 321.002,
	"priceId": 1,
    "premium": true
}

http://localhost:9000/users/signIn

Content-Type: application/json

{
"login":"farabdullaev@gmail.com",
"password":"fara321"
}

==========================================================

http://localhost:9000/search/params

Content-Type: application/json

//SearchParams(location: Option[Int],
//            checkInDate: Date = new Date(),
//            checkOutDate: Date = new Date(),
//            roomType: Option[Int],
//            adults: Option[Int],
//            children: Option[Int],
//            bottom:Option[Double],
//            top: Option[Double],
//            hotelTypeId: Option[Int],
//            starRating: Option[Int],
//            reviewScore: Option[Int]
//           )

{
	"cityId":1,
	"checkInDate":"10/21/2015 14:00",
	"checkOutDate":"10/25/2015 12:00",
	"roomType": 1,
	"adults": 1,
	"bottom":5.0,
	"top": 100.0,
	"starRating": 5,
	"hotelTypeId": 1
}
