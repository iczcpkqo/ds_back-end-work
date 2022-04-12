'''
Author: Guowen Liu
Date: 2022-04-12 11:22:45
LastEditors: Guowen Liu
LastEditTime: 2022-04-12 21:15:35
Copyright (c) 2022 by Guowen Liu, All Rights Reserved. 
'''
import random
import uuid

import locust
from locust import HttpUser, task, run_single_user, between
from locust.env import Environment

password = "123456"

def create_random_user():
    name = str(uuid.uuid4())

    email = name + "@wada.com"
    body = {
        "name": name,
        "email": email,
        "password": password,
        "location": {
            "country": random.choice(["India", "China"]),
            "region": "Asia"
        },
        "isAthlete": random.choice([True, False])
    }
    return body


class MyUser(HttpUser):
    wait_time = between(1, 2)
    _REGISTRATION_URL = '/user/register'
    _LOCATIONS_URL = '/locations'

    @locust.task
    def locations(self):
        resp = self.client.get(self._LOCATIONS_URL)
        print(resp.content)
        assert resp.status_code == 200

    @locust.task
    def registration(self):
        user_body = create_random_user()
        resp = self.client.post(self._REGISTRATION_URL, user_body)
        print(resp.content)
        assert resp.status_code == 200
