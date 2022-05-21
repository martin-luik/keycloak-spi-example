<#macro registrationLayout bodyClass="" displayMessage=false>
    <!DOCTYPE html>
    <html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
        <title>SSO Example</title>
    </head>
    <body>
    <#nested "application">
    <div class="container">
        <#nested "form">
    </div>
    </body>
    </html>
</#macro>
