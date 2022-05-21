<#import "components/auth-method-input.ftl" as authMethodInput>
<#import "components/auth-button.ftl" as authButton>
<#import "components/field/person-no-input-field.ftl" as personNoInputField>

<#macro component currentAuthMethod>
    <form id="login-form-${currentAuthMethod}"
         onsubmit="login.disabled = true; return true;"
         action="${url.loginAction}"
         method="post"
         class="login-form hidden"
    >
        <@authMethodInput.component currentAuthMethod=currentAuthMethod />

        <@personNoInputField.component />

        <@authButton.component currentAuthMethod=currentAuthMethod />
    </form>
</#macro>
