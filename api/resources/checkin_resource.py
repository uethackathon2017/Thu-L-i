from flask_restful import Resource, reqparse
import mlab
from models.checkin_day import CheckinClass


class Checkin(Resource):
    def get(self):
        checkin = CheckinClass.objects()
        return mlab.item2json(checkin)

    def post(self):
        parser = reqparse.RequestParser()
        parser.add_argument(name="name", type=str, location="json")
        parser.add_argument(name="date", type=str, location="json")
        parser.add_argument(name="students", type=list, location="json")
        body = parser.parse_args()
        name = body.name
        date = body.date
        for checkin_res in CheckinClass.objects():
            if checkin_res.name==name:
                if checkin_res.date==date:
                    return {"message":"data existed"},401
        students = body["students"]
        checkin = CheckinClass(name=name, date=date, students=students)
        checkin.save()
        add_checkin=CheckinClass.objects().with_id(checkin.id)
        return mlab.item2json(add_checkin)
