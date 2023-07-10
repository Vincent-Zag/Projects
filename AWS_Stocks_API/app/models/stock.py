from .db import db, environment, SCHEMA, add_prefix_for_prod

class Stock(db.Model):
    __tablename__ = 'stocks'

    if environment == "production":
        __table_args__ = {'schema': SCHEMA}

    id = db.Column(db.Integer, primary_key=True)
    name = db.Column(db.String(40), nullable=False, unique=True)
    symbol = db.Column(db.String(40), nullable=False, unique=True)
    price = db.Column(db.String, nullable=False)
    percent_change = db.Column(db.String, nullable=False)
    market_cap = db.Column(db.String, nullable=False)
    revenue = db.Column(db.String, nullable=False)
    timestamp= db.Column(db.String)





    def to_dict(self):
        return {
            'id': self.id,
            'name': self.name,
            'price': self.price,
            'symbol': self.symbol,
            'percent_change': self.percent_change,
            'market_cap': self.market_cap,
            'revenue': self.revenue,
            'timestamp': self.timestamp
        }
