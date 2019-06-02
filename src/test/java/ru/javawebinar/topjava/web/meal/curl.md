#method get();
curl -v http://localhost:8080/topjava/rest/meals/100002

#method delete();
curl -vX DELETE http://localhost:8080/topjava/rest/meals/100002

#method getAll(); without date,time parameters
curl -v http://localhost:8080/topjava/rest/meals

#method getAll(); with date,time parameters
curl -v "http://localhost:8080/topjava/rest/meals?startDate=2015-05-30&startTime=19:00&endDate=2015-05-30&endTime=21:00"

#method createWithLocation();
curl -vd '{"dateTime":"2015-07-30T20:00:00","description":"Ужин","calories":500}' -H 'Content-Type: application/json' http://localhost:8080/topjava/rest/meals

#method update();
curl -vd '{"id": 100006,"dateTime":"2015-07-30T20:00:00","description":"UPDATED","calories":500}' -H 'Content-Type: application/json' -X PUT http://localhost:8080/topjava/rest/meals/100006



