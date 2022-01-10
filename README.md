# 5e_magic_item_prices
Project for tracking the prices, descriptions, and properties of magic items from Dungeons and Dragons 5e. 
This is another side project from my time at bootcamp that I want to build into a full-functioning website so that folks can use it for their games!

## Database Build Tools
This project pulls from the 5e API (http://www.dnd5eapi.co/) to source Magic Item data from the Dungeon Master's Guide as a starting point.
###### LoadFrom5eAPI
this is a program which pulls from the 5e API uses RESTful API tools and parses JSON data to load into a custom database.

## Server
A server-side RESTful API which allows clients to query for Magic Item data based on name, rarity, type, or attunement. Returns item information along with 

## Background
The idea for this project came about when my Dungeon Master was reading through a campaign module and stumbled across a price for a magic item they had previously sold to a player for Way Too Cheap. This information had not been in the Dungeon Masters guide where the information about the item is originally published. I figured there were probably more cases where prices might be scattered across sourcebooks, DnD Beyond, and other official materials. So I decided to throw together this project to compile them all!

In the future I'd like to add a feature that allows users to create accounts, generate and save their own custom prices for items.