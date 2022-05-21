<#import "template.ftl" as layout>
<#import "components/forms/dev-login-form.ftl" as devLoginForm>

<@layout.registrationLayout; section>
    <#if section = "form">
        <div class="content">
            <h1 class="heading">${msg("heading")}</h1>
            <div class="login-wrapper">
                <@devLoginForm.component currentAuthMethod="ID_CODE" />
            </div>
        </div>
    </#if>
</@layout.registrationLayout>
