from flask_wtf import FlaskForm
from wtforms import StringField, DateField, IntegerField
from wtforms.validators import DataRequired, ValidationError

class StockForm(FlaskForm):
    name = StringField('name', validators=[DataRequired()])
    symbol = StringField('symbol', validators=[DataRequired()])
    price = StringField("price", validators=[DataRequired()])
    percent_change = StringField("percent_change", validators=[DataRequired()])
    market_cap = StringField("market_cap", validators=[DataRequired()])
    revenue= StringField("revenue", validators=[DataRequired()])
    timestamp= StringField("timestamp", validators=[DataRequired()])
