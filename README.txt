JSF PROGRAM 

Program selects orders with highest value posible among others. 
Program pick with certian logic:
If first order have a value lower then second and second order possible pick time is smaller than
first order completion time.

Variables:
gson - builds a reader to read orders from Json
gsonS - builds a reader to read store from Json
OrderType - maps List of orders to Type
Store_reader - path to read store json file
Order_reader - path to read order json file
jsonStore - readed store from json
jsonOrdersList - readed order from json
jsonOrdersList.sort(new OrderComparator()); - this line sorts by time order should be pick to complete it
next_pick - used to bind time when picker will complete his job
possible_take - used to check if picker can pick this order based on time he finished his job
higher_value_take - used to check if picker can pick order with higher value if exists
lookup - used to check if order with higher value doesn't start after order before was compleated
posible_value - holds value of order with higher value if ocures
CompleatedOrders - list of orders that have been compleated
ordersTaken - used to check if certian order isn't taken

Classes:

Main - execution of algoritm
OrderComparator - used to customize comparator to sort Array by earliest time order should be pick
Orders - class that represent orders object readed from json
Store - class that represent store object readed from json
StoreAdapter - class used to customize store deserialization from json
OrderAdapter - class used to customize order deserialization from json

Tests:

OrderComparatorTest - test if comparator can differentiate from two order object and 
can sort properly based on earliest time order should be pick

OrdersAdapterTest - test if adapter deserialized properly order object from json

StoreAdapterTest - test if adapter deserialized properly store object from json



