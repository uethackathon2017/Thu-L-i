from flask_restful import Resource, reqparse
from models.user import User
import mlab


class UserRegister(Resource):
    def post(self):
        parser = reqparse.RequestParser()
        parser.add_argument(name="name", type=str, location="json")
        parser.add_argument(name="password", type=str, location="json")
        body = parser.parse_args()
        name = body["name"]
        for user_res in User.objects():
            if user_res.name == name:
                return {"message": "Username existed!"},401

        password = body["password"]
        user = User(name=name, password=password)
        user.save()
        add_user = User.objects().with_id(user.id)
        return mlab.item2json(add_user)


class UserLogin(Resource):
    def post(self):
        parser = reqparse.RequestParser()
        parser.add_argument(name="name", type=str, location="json")
        parser.add_argument(name="password", type=str, location="json")
        body = parser.parse_args()
        name = body["name"]
        password = body["password"]

        for user_data in User.objects:
            if user_data.name == name:
                if user_data.password == password:
                    return mlab.item2json(user_data)
        return {"message": " Username or password doesn't not match"},401
