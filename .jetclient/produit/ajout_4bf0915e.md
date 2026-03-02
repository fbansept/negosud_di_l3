```toml
name = 'ajout'
method = 'POST'
url = 'http://localhost:8080/api/produit'
sortWeight = 2000000
id = '4bf0915e-1bfe-4344-9b8d-05c4a80fe245'

[body]
type = 'JSON'
raw = '''
{
  "nom" : "beaujolais xxx",
  "prix" : 5.5,
  "famille" : {
    "id": 1,
   
  }
}'''
```
