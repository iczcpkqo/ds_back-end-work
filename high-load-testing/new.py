# prerequisite
# 1000 athlete 
# 100 ADO

# athlete flow
# athlete books availability
# athlete updates availability
# athlete deletes availability


# ado flow
# athlete books availability
# ado books appointment

import requests
import random

BASE = "http://52.190.2.8:8080"
GET_LOCATIONS = BASE + "/location"
GET_ALL_ATHLETES = BASE + "/athlete"
LOGIN_URL = BASE + '/user/login'

def athletes_login():
    # _SERVICE_POST_LOGIN = {"email": "athlete_liu@wada.com","password": "athlete_liu"}
    # _HEADERS = {'content-type': 'application/json'}
    
    # resp = requests.request("POST", LOGIN_URL, json=_SERVICE_POST_LOGIN, headers=_HEADERS)
    # assert resp.status_code == 200
    # json_response_dict = resp.json()
    _TOKEN = "Bearer " + "eyJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJTbWFydCBDaXR5IE9yZyIsInN1YiI6IkFrdWwgUm9iZXJ0c29uIiwiZXhwIjoxNjUwMzMxNDIyLCJpYXQiOjE2NTAzMjc4MjJ9.91rVT16XfQT54n6HGDiD6XDaAiqAKDNA7__jFXjibYk"
    # _ID = json_response_dict['id']
    _current_headers = {'content-type': 'application/json',"Authorization": _TOKEN}

    # print(f'login : Athletes login success! Token:{_TOKEN} ID:{_ID} \r\n')
    return _current_headers

def get_locations():
    return requests.request("GET", GET_LOCATIONS).json()['locations']

def get_random_from_list(list):
    if (len(list) <= 0):
        return None
    return list[random.randint(0, len(list)-1)]

def get_all_athletes():
    return requests.request("GET", GET_ALL_ATHLETES, headers=_HEADERS).json()

_HEADERS = athletes_login()
_LOCATIONS = get_locations()
_ATHLETES = get_all_athletes()
print(f'Init complete {len(_LOCATIONS)}, {len(_ATHLETES)}')