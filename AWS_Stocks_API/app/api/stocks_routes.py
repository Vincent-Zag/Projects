from app.models import Stock, db
from flask import Blueprint, request, jsonify
from app.forms import StockForm, EditForm


stock_routes = Blueprint('stocks', __name__)

@stock_routes.route('')
def get_stock():
    data = Stock.query.all()
    return {stock.to_dict()['id']: stock.to_dict() for stock in data}

@stock_routes.route('/<int:id>')
def get_stock_id(id):
    data = Stock.query.get(id)
    return data.to_dict()

@stock_routes.route('', methods=['POST'])
def stock_purchase():
    form = StockForm()
    print(form.data)
    stock_upload = Stock(
        name = form.data['name'],
        symbol = form.data['symbol'],
        price = form.data['price'],
        percent_change = form.data['percent_change'],
        market_cap = form.data['market_cap'],
        revenue = form.data['revenue'],
        timestamp = form.data['timestamp']
        )
    db.session.add(stock_upload)
    db.session.commit()
    return stock_upload.to_dict()


@stock_routes.route('/<int:id>', methods=['PUT'])
def edit_stock(id):
    stock = Stock.query.get(id)
    form = StockForm()
    stock.name = form.data['name'],
    stock.symbol = form.data['symbol'],
    stock.price = form.data['price'],
    stock.percent_change = form.data['percent_change'],
    stock.market_cap = form.data['market_cap'],
    stock.revenue = form.data['revenue'],
    stock.timestamp = form.data['timestamp']
    db.session.add(stock)
    return stock.to_dict()

@stock_routes.route('/<int:id>', methods=['DELETE'])
def delete_stock(id):
    stock = Stock.query.get(id)
    if id == stock.id:
        db.session.delete(stock)
        db.session.commit()
        return {"data": "Deleted"}
    return {'errors': ['Unauthorized']}
pass
