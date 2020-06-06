"""
This file defines actions, i.e. functions the URLs are mapped into
The @action(path) decorator exposed the function at URL:

    http://127.0.0.1:8000/{app_name}/{path}

If app_name == '_default' then simply

    http://127.0.0.1:8000/{path}

If path == 'index' it can be omitted:

    http://127.0.0.1:8000/

The path follows the bottlepy syntax.

@action.uses('generic.html')  indicates that the action uses the generic.html template
@action.uses(session)         indicates that the action uses the session
@action.uses(db)              indicates that the action uses the db
@action.uses(T)               indicates that the action uses the i18n & pluralization
@action.uses(auth.user)       indicates that the action requires a logged in user
@action.uses(auth)            indicates that the action requires the auth object

session, db, T, auth, and tempates are examples of Fixtures.
Warning: Fixtures MUST be declared with @action.uses({fixtures}) else your app will result in undefined behavior
"""

import uuid

from py4web import action, request, abort, redirect, URL, Field
from py4web.utils.form import Form, FormStyleBulma
from py4web.utils.url_signer import URLSigner

from yatl.helpers import A
from . common import db, session, T, cache, auth, signed_url
import datetime


url_signer = URLSigner(session)

# The auth.user below forces login.
@action('index')
@action.uses(auth.user, url_signer, session, db, 'index.html')
def index():
    return dict(
        # This is an example of a signed URL for the callback.
        # See the index.html template for how this is passed to the javascript.
        notes_url = URL('notes', signer = url_signer),
        delete_url = URL('delete_note', signer = url_signer),
        callback_url = URL('callback', signer=url_signer),
    )

@action('notes', method="GET")
@action.uses(db, auth.user, session, url_signer.verify())
def get_notes():
    notes = db((db.note.email == auth.current_user.get('email'))).select(orderby=~db.note.date_accessed).as_list()
    #notes = db(db.note).select(orderby=~db.note.date_accessed).as_list()
    return dict(notes = notes)

@action('notes', method="POST")
@action.uses(db, auth.user)
def save_note():
    id = request.json.get('id')
    title = request.json.get('title')
    content = request.json.get('content')
    colour = request.json.get('colour')
    rating = request.json.get('rating')
    date_accessed = datetime.datetime.utcnow()

    if id is None:
        id = db.note.insert(title = title, content = content, colour = colour, rating = rating, date_accessed = date_accessed, email = auth.current_user.get('email'),)
    else:
        db(db.note.id == id).update(title = title, content = content, colour = colour, rating = rating, date_accessed = date_accessed,)

    return dict(title = title, content = content, colour = colour, rating = rating, id = id, date_accessed = date_accessed)

@action('delete_note', method="POST")
@action.uses(db, auth.user, session, url_signer.verify())
def delete_note():
    db((db.note.email == auth.current_user.get("email")) & (db.note.id == request.json.get('id'))).delete()
    #db(db.note.id == request.json.get('id')).delete()
    return "ok"