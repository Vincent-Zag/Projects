from flask_wtf import FlaskForm
from wtforms import StringField, DateField, IntegerField
from wtforms.validators import DataRequired, ValidationError

class EditForm(FlaskForm):
    name = StringField('name', validators=[DataRequired()])
    symbol = StringField('symbol', validators=[DataRequired()])
    price = IntegerField("price", validators=[DataRequired()])
    percent_change = IntegerField("percent_change", validators=[DataRequired()])
    market_cap = IntegerField("market_cap", validators=[DataRequired()])
    revenue= IntegerField("revenue", validators=[DataRequired()])
    timestamp= StringField("timestamp", validators=[DataRequired()])
